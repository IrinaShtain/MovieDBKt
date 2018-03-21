package ua.shtain.irina.moviedbkt.view.screens.home.common.movie_details.reviews

import android.util.Log
import io.reactivex.disposables.CompositeDisposable
import ua.shtain.irina.moviedbkt.model.exceptions.ConnectionException
import ua.shtain.irina.moviedbkt.model.movie.review.ReviewItem
import ua.shtain.irina.moviedbkt.other.Constants
import ua.shtain.irina.moviedbkt.view.base.content.ContentView
import ua.shtain.irina.moviedbkt.view.screens.home.common.movie_details.reviews.adapter.ReviewItemDH
import java.util.ArrayList
import javax.inject.Inject

/**
 * Created by Irina Shtain on 20.03.2018.
 */
class ReviewsPresenter @Inject constructor(compositeDisposable: CompositeDisposable,
                                           model: ReviewsContract.Model) : ReviewsContract.Presenter {

    lateinit var mView: ReviewsContract.View
    private var mCompositeDisposable = compositeDisposable
    private var mModel = model
    private var movieID = 0

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
        movieID = mView.getMovieID()
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
                mModel.getReviews(movieID, pageNumber)
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
}