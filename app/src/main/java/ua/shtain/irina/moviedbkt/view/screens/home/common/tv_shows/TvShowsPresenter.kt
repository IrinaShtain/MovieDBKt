package ua.shtain.irina.moviedbkt.view.screens.home.common.tv_shows

import android.util.Log
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import ua.shtain.irina.moviedbkt.model.exceptions.ConnectionException
import ua.shtain.irina.moviedbkt.model.tv.SearchTvShowResponse
import ua.shtain.irina.moviedbkt.model.tv.TvShowItem
import ua.shtain.irina.moviedbkt.other.Constants
import ua.shtain.irina.moviedbkt.view.base.content.ContentView
import ua.shtain.irina.moviedbkt.view.screens.home.common.tv_shows.adapter.TvShowItemDH

/**
 * Created by Irina Shtain on 02.03.2018.
 */
abstract class TvShowsPresenter : TvShowsContract.Presenter {

    lateinit var mView: TvShowsContract.View
    protected lateinit var mCompositeDisposable: CompositeDisposable
    protected lateinit var mModel: TvShowsContract.Model

    private var mCurrentPage: Int = 0
    private var mTotalPages = Integer.MAX_VALUE
    private var mNeedRefresh = true
    protected var searchType: Int = 0
    private var totalResults: Int = 0

    abstract fun getShows(page: Int): Observable<SearchTvShowResponse>

    override fun setView(view: ContentView) {
        mView = view as TvShowsContract.View
    }

    override fun subscribe() {
        searchType = mView.getShowsType()
        if (mNeedRefresh) {
            loadTvShows()
        } else
            mNeedRefresh = true
    }

    override fun unsubscribe() {
        mCompositeDisposable.clear()
    }

    override fun onRefresh() {
        loadTvShows()
    }

    override fun getNextPage() {
        if (mCurrentPage < mTotalPages) {
            mView.showProgressPagination()
            loadPage(++mCurrentPage)
        }
    }

    override fun updateNeedRefresh(needRefresh: Boolean) {
        mNeedRefresh = needRefresh
    }

    private fun loadTvShows() {
        mView.showProgressMain()
        mCurrentPage = 1
        mTotalPages = Integer.MAX_VALUE
        loadPage(mCurrentPage)
    }

    private fun loadPage(pageNumber: Int) {
        mCompositeDisposable.add(getShows(pageNumber)
                .subscribe({ response ->
                    mView.hideProgress()
                    mCurrentPage = pageNumber
                    mTotalPages = response.totalPages
                    totalResults = response.totalResults
                    if (mCurrentPage == 1)
                        if (!response.tvShows.isEmpty())
                            mView.setList(prepareTvShowList(response.tvShows))
                        else
                            mView.showPlaceholder(Constants.PlaceholderType.EMPTY)
                    else
                        mView.addList(prepareTvShowList(response.tvShows))

                }) { throwable ->
                    mView.hideProgress()
                    throwable.printStackTrace()
                    when {
                        mTotalPages != Integer.MAX_VALUE -> when (throwable) {
                            is ConnectionException -> mView.showMessage(Constants.MessageType.CONNECTION_PROBLEMS)
                            else -> mView.showMessage(Constants.MessageType.UNKNOWN)
                        }
                        throwable is ConnectionException -> mView.showPlaceholder(Constants.PlaceholderType.NETWORK)
                        else -> mView.showPlaceholder(Constants.PlaceholderType.UNKNOWN)
                    }
                })
    }

    private fun prepareTvShowList(items: ArrayList<TvShowItem>): ArrayList<TvShowItemDH> {
        val dhs = items.mapTo(ArrayList()) { TvShowItemDH(it) }
        if (searchType == Constants.TYPE_FAVORITE_TV_SHOWS || searchType == Constants.TYPE_WATCHLIST_TV_SHOWS)
            for (dh in dhs) dh.isInList = true
        return dhs
    }

    override fun deletionConfirmed(itemId: Int, position: Int) {
        when (searchType) {
            Constants.TYPE_FAVORITE_TV_SHOWS -> mCompositeDisposable.add(mModel.deleteFromFavoriteTV(itemId)
                    .subscribe({ _ ->
                        mView.hideProgress()
                        mView.showMessage(Constants.MessageType.MOVIE_WAS_DELETED)
                        mView.updateTvShows(position)
                        --totalResults
                        checkEmptyList()
                    }, { throwable ->
                        Log.e("myLog", "throwable " + throwable.localizedMessage)
                        mView.hideProgress()
                        when {
                            throwable.message.equals("HTTP 500 Internal Server Error") -> { // backend's bug :(
                                mView.showMessage(Constants.MessageType.TV_SHOW_WAS_DELETED)
                                mView.updateTvShows(position)
                                --totalResults
                                checkEmptyList()
                            }
                            throwable is ConnectionException -> mView.showMessage(Constants.MessageType.CONNECTION_PROBLEMS)
                            else -> mView.showMessage(Constants.MessageType.UNKNOWN)
                        }
                    }))
            Constants.TYPE_WATCHLIST_TV_SHOWS -> mCompositeDisposable.add(mModel.deleteFromWatchListTV(itemId)
                    .subscribe({ _ ->
                        mView.hideProgress()
                        mView.showMessage(Constants.MessageType.TV_SHOW_WAS_DELETED)
                        mView.updateTvShows(position)
                        --totalResults
                        checkEmptyList()
                    }, { throwable ->
                        Log.e("myLog", "throwable " + throwable.localizedMessage)
                        mView.hideProgress()
                        when {
                            throwable.message.equals("HTTP 500 Internal Server Error") -> { // backend's bug :(
                                mView.showMessage(Constants.MessageType.LIST_WAS_DELETED)
                                mView.updateTvShows(position)
                                --totalResults
                                checkEmptyList()
                            }
                            throwable is ConnectionException -> mView.showMessage(Constants.MessageType.CONNECTION_PROBLEMS)
                            else -> mView.showMessage(Constants.MessageType.UNKNOWN)
                        }
                    }))
        }
    }

    private fun checkEmptyList() {
        if (totalResults == 0)
            mView.showPlaceholder(Constants.PlaceholderType.EMPTY)
    }

    override fun deleteTvShow(showID: Int, position: Int) {
        mView.showAlert(showID, position)
    }
}