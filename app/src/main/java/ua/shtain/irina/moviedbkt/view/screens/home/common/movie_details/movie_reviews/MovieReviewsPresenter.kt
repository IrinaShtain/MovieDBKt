package ua.shtain.irina.moviedbkt.view.screens.home.common.movie_details.movie_reviews

import io.reactivex.disposables.CompositeDisposable
import ua.shtain.irina.moviedbkt.view.screens.home.common.reviews.ReviewsContract
import ua.shtain.irina.moviedbkt.view.screens.home.common.reviews.ReviewsPresenter
import javax.inject.Inject

/**
 * Created by Irina Shtain on 26.03.2018.
 */
class MovieReviewsPresenter @Inject constructor(compositeDisposable: CompositeDisposable,
                                                model: ReviewsContract.Model) : ReviewsPresenter() {
    init {
        mModel = model
        mCompositeDisposable = compositeDisposable
    }

    override fun getReviews(mID: Int, pageNumber: Int) = mModel.getMovieReviews(mID, pageNumber)
}