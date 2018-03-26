package ua.shtain.irina.moviedbkt.view.screens.home.common.reviews

import android.util.Log
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import ua.shtain.irina.moviedbkt.model.exceptions.ConnectionException
import ua.shtain.irina.moviedbkt.model.movie.SearchMovieResponse
import ua.shtain.irina.moviedbkt.model.movie.review.ReviewItem
import ua.shtain.irina.moviedbkt.model.movie.review.ReviewResponse
import ua.shtain.irina.moviedbkt.other.Constants
import ua.shtain.irina.moviedbkt.view.base.content.ContentView
import ua.shtain.irina.moviedbkt.view.screens.home.common.reviews.adapter.ReviewItemDH
import java.util.ArrayList
import javax.inject.Inject

/**
 * Created by Irina Shtain on 20.03.2018.
 */
abstract class ReviewsPresenter  : ReviewsContract.Presenter {

    lateinit var mView: ReviewsContract.View
    protected lateinit var mCompositeDisposable :CompositeDisposable
    protected lateinit var mModel : ReviewsContract.Model
    protected var mID = 0

    private var mTotalPages = Integer.MAX_VALUE
    private var mCurrentPage: Int = 0

    private val throwableConsumer = { throwable: Throwable ->
        Log.d("myLogs", "Error! " + throwable.message)
        throwable.printStackTrace()
        mView.hideProgress()
        if (mTotalPages != Integer.MAX_VALUE)
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
        mView = view as ReviewsContract.View
    }

    override fun subscribe() {
        mID = mView.getID()
        mView.showProgressMain()
        loadPage(1)
    }

    override fun unsubscribe() {
        mCompositeDisposable.clear()
    }

    override fun onRefresh() {
        loadPage(1)
    }

    override fun getNextPage() {
        if (mCurrentPage < mTotalPages) {
            mView.showProgressPagination()
            loadPage(++mCurrentPage)
        }
    }

    private fun loadPage(pageNumber: Int) {
        mCompositeDisposable.add(
                getReviews(mID, pageNumber)
                        .subscribe({ response ->
                            mView.hideProgress()
                            mCurrentPage = response.page
                            mTotalPages = response.totalPages
                            if (pageNumber == 1) {
                                if (response.reviews.isEmpty())
                                    mView.showPlaceholder(Constants.PlaceholderType.EMPTY)
                                else
                                    mView.setList(prepareList(response.reviews))
                            } else {
                                mView.addList(prepareList(response.reviews))
                            }
                        }, throwableConsumer))
    }

    private fun prepareList(items: ArrayList<ReviewItem>): ArrayList<ReviewItemDH> {
        return items.mapTo(ArrayList()) { ReviewItemDH(it) }
    }

    abstract fun getReviews(mID: Int, pageNumber: Int): Observable<ReviewResponse>
}