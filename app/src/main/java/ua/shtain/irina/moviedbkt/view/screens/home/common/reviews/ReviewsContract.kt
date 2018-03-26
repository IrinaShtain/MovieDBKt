package ua.shtain.irina.moviedbkt.view.screens.home.common.reviews

import io.reactivex.Observable
import ua.shtain.irina.moviedbkt.model.movie.review.ReviewResponse
import ua.shtain.irina.moviedbkt.view.base.refresheble_content.RefreshablePresenter
import ua.shtain.irina.moviedbkt.view.base.refresheble_content.RefreshableView
import ua.shtain.irina.moviedbkt.view.screens.home.common.reviews.adapter.ReviewItemDH

/**
 * Created by Irina Shtain on 20.03.2018.
 */
interface ReviewsContract {
    interface View : RefreshableView {
        fun setList(reviewItemDHs: MutableList<ReviewItemDH>)
        fun addList(reviewItemDHs: MutableList<ReviewItemDH>)
        fun getID(): Int
    }

    interface Presenter : RefreshablePresenter {
        fun getNextPage()
    }

    interface Model {
        fun getMovieReviews(movieID: Int, page: Int): Observable<ReviewResponse>
        fun getTvShowsReviews(showID: Int, page: Int): Observable<ReviewResponse>
    }
}