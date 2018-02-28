package ua.shtain.irina.moviedbkt.view.screens.home.common.movie_details.rating_dialog

import io.reactivex.Observable
import ua.shtain.irina.moviedbkt.model.lists.ResponseMessage
import ua.shtain.irina.moviedbkt.view.base.IBasePresenter
import ua.shtain.irina.moviedbkt.view.base.IBaseView

/**
 * Created by Irina Shtain on 28.02.2018.
 */
interface RatingDialogContract {
    interface View : IBaseView {
        fun updateTargetFragment(resultCode: Int)
        fun showProgress()
        fun hideProgress()
        fun showRatingError()
        fun hideRatingError()
        fun getMovieID(): Int
    }

    interface Presenter : IBasePresenter<RatingDialogContract.View> {
        fun sendRating(rating: Float)
    }

    interface Model {
        fun rateMovie(rating: Float, movieID: Int): Observable<ResponseMessage>
    }

}