package ua.shtain.irina.moviedbkt.view.screens.home.stars

import android.util.Log
import io.reactivex.disposables.CompositeDisposable
import ua.shtain.irina.moviedbkt.model.exceptions.ConnectionException
import ua.shtain.irina.moviedbkt.model.star.StarItem
import ua.shtain.irina.moviedbkt.other.Constants
import ua.shtain.irina.moviedbkt.view.base.content.ContentView
import ua.shtain.irina.moviedbkt.view.screens.home.stars.adapter.StarDH
import java.util.*
import javax.inject.Inject

/**
 * Created by Irina Shtain on 01.03.2018.
 */
class SearchStarPresenter @Inject constructor(compositeDisposable: CompositeDisposable,
                                              model: SearchStarContract.Model) : SearchStarContract.Presenter {
    lateinit var mView: SearchStarContract.View
    private var mCompositeDisposable = compositeDisposable
    private var mModel = model

    private var totalPages = Integer.MAX_VALUE
    private var currentPage: Int = 0
    private var name: String = ""

    override fun setView(view: ContentView) {
        mView = view as SearchStarContract.View
    }

    override fun subscribe() {

    }

    override fun unsubscribe() {
        mCompositeDisposable.clear()
    }

    override fun onRefresh() {
        if (!name.isEmpty()) {
            currentPage = 1
            totalPages = Integer.MAX_VALUE
            loadPage(currentPage)
        }
        else
            mView.hideProgress()
    }

    override fun onSearchClick(star: String) {
        if (!star.isEmpty()) {
            this.name = star
            currentPage = 1
            mView.showProgressMain()
            loadPage(currentPage)
        } else
            mView.showMessage(Constants.MessageType.INPUT_STAR_NAME)
    }

    override fun getNextPage() {
        Log.e("myLog", "current_page" + currentPage)
        if (currentPage < totalPages) {
            mView.showProgressPagination()
            loadPage(currentPage + 1)
        }
    }

    private fun loadPage(pageNumber: Int) {
        mCompositeDisposable.add(mModel.getStars(name, pageNumber)
                .subscribe({ response ->
                    currentPage = pageNumber
                    totalPages = response.totalPages
                    mView.hideProgress()
                    when (currentPage) {
                        1 -> if (!response.stars.isEmpty())
                            mView.setList(prepareList(response.stars))
                        else
                            mView.showPlaceholder(Constants.PlaceholderType.EMPTY)
                        else -> mView.addList(prepareList(response.stars))
                    }

                }, { throwable ->
                    mView.hideProgress()
                    when {
                        totalPages == Integer.MAX_VALUE -> if (throwable is ConnectionException) {
                            mView.showMessage(Constants.MessageType.CONNECTION_PROBLEMS)
                        } else {
                            mView.showMessage(Constants.MessageType.UNKNOWN)
                        }
                        throwable is ConnectionException -> mView.showPlaceholder(Constants.PlaceholderType.NETWORK)
                        else -> mView.showPlaceholder(Constants.PlaceholderType.UNKNOWN)
                    }
                }))
    }

    private fun prepareList(items: ArrayList<StarItem>) = items.mapTo(ArrayList()) { StarDH(it) }
}