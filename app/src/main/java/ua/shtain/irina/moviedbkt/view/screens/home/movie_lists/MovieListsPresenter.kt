package ua.shtain.irina.moviedbkt.view.screens.home.movie_lists

import android.util.Log
import io.reactivex.disposables.CompositeDisposable
import ua.shtain.irina.moviedbkt.model.exceptions.ConnectionException
import ua.shtain.irina.moviedbkt.model.lists.ListItem
import ua.shtain.irina.moviedbkt.other.Constants
import ua.shtain.irina.moviedbkt.root.session.ISessionManager
import ua.shtain.irina.moviedbkt.view.base.content.ContentView
import ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.adapter.CreatedListsDH
import java.util.ArrayList
import javax.inject.Inject

/**
 * Created by Irina Shtain on 15.02.2018.
 */
class MovieListsPresenter @Inject constructor(sessionManager: ISessionManager,
                                              compositeDisposable: CompositeDisposable,
                                              model: MovieListsContract.Model) : MovieListsContract.Presenter {
    lateinit var mView: MovieListsContract.View

    private var mSessionManager = sessionManager
    private var mCompositeDisposable = compositeDisposable
    private var mModel = model

    private var totalPages = Integer.MAX_VALUE
    private var currentPage: Int = 0
    private var totalResults: Int = 0

    private val throwableConsumer = { throwable: Throwable ->
        Log.d("myLogs", "Error! " + throwable.message)
        throwable.printStackTrace()
        mView.hideProgress()
        if (totalPages != Integer.MAX_VALUE)
            if (throwable is ConnectionException) {
                mView.showMessage(Constants.MessageType.CONNECTION_PROBLEMS)
            } else {
                mView.showMessage(Constants.MessageType.UNKNOWN)
            }
        else if (throwable is ConnectionException) {
            mView.showPlaceholder(Constants.PlaceholderType.NETWORK)
        } else {
            mView.showPlaceholder(Constants.PlaceholderType.UNKNOWN)
        }
    }

    override fun setView(view: ContentView) {
        mView = view as MovieListsContract.View
    }

    override fun subscribe() {
        mView.showProgressMain()
        loadPage(1)
    }

    override fun showDetails(lisID: Int, item: CreatedListsDH) {
        mView.openListDetails(lisID, item.getListsName())
    }

    override fun removeList(item: CreatedListsDH, pos: Int) {
        mView.showProgressPagination()
        mCompositeDisposable.add(mModel.deleteList(item.getListsID())
                .subscribe({ _ ->
                    mView.hideProgress()
                    mView.deleteItem(pos)
                    --totalResults
                    checkEmptyList()
                }, { throwable ->
                    mView.hideProgress()
                    Log.e("myLog", "throwable " + throwable.localizedMessage)
                    when {
                        throwable.message.equals("HTTP 500 Internal Server Error") -> {// backend bug :(
                            mView.deleteItem(pos)
                            --totalResults
                            checkEmptyList()
                        }
                        throwable is ConnectionException -> mView.showMessage(Constants.MessageType.CONNECTION_PROBLEMS)
                        else -> mView.showMessage(Constants.MessageType.UNKNOWN)
                    }
                }))
    }

    private fun checkEmptyList() {
        if (totalResults == 0)
            mView.showPlaceholder(Constants.PlaceholderType.EMPTY)
    }

    override fun unsubscribe() {
        mCompositeDisposable.clear()
    }

    override fun onRefresh() {
        mView.showProgressMain()
        loadPage(1)
    }

    override fun getNextPage() {
        if (currentPage < totalPages) {
            mView.showProgressPagination()
            loadPage(currentPage + 1)
        }
    }

    override fun loadPage(pageNumber: Int) {
        mCompositeDisposable.add(
                mModel.getLists(mSessionManager.getUserData().id, pageNumber)
                        .subscribe({ userListResponse ->
                            mView.hideProgress()
                            totalPages = userListResponse.totalPages
                            currentPage = pageNumber
                            totalResults = userListResponse.totalResults
                            if (pageNumber == 1) {
                                if (userListResponse.lists.isEmpty())
                                    mView.showPlaceholder(Constants.PlaceholderType.EMPTY)
                                else
                                    mView.setLists(prepareList(userListResponse.lists))
                            } else {
                                // TODO: 15.02.2017 Backend returns duplicate
                                mView.addLists(prepareList(userListResponse.lists))
                            }
                        }, throwableConsumer))
    }

    private fun prepareList(items: ArrayList<ListItem>): ArrayList<CreatedListsDH> {
        return items.mapTo(ArrayList()) { CreatedListsDH(it) }
    }

    override fun showResult(resultID: Int, title: String, description: String) {
        when (resultID) {
            Constants.ERROR_CODE_CONNECTION_LOST -> showConnectionError()
            Constants.ERROR_CODE_UNKNOWN -> showUnknownError()
            else -> {
                mView.showMessage(Constants.MessageType.NEW_LIST_CREATED_SUCCESSFULLY)
                ++totalResults
                mView.addItem(CreatedListsDH(ListItem(title, "", description, "", "", 0, 0, resultID)))
            }
        }
    }

    private fun showConnectionError() {
        if (totalPages != Integer.MAX_VALUE)
            mView.showMessage(Constants.MessageType.CONNECTION_PROBLEMS)
        else
            mView.showPlaceholder(Constants.PlaceholderType.NETWORK)
    }

    private fun showUnknownError() {
        if (totalPages != Integer.MAX_VALUE)
            mView.showMessage(Constants.MessageType.UNKNOWN)
        else
            mView.showPlaceholder(Constants.PlaceholderType.UNKNOWN)
    }

}