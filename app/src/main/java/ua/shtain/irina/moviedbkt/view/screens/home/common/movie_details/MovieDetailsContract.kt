package ua.shtain.irina.moviedbkt.view.screens.home.common.movie_details

import io.reactivex.Observable
import ua.shtain.irina.moviedbkt.model.lists.ResponseMessage
import ua.shtain.irina.moviedbkt.model.movie.MovieItem
import ua.shtain.irina.moviedbkt.view.base.IBasePresenter
import ua.shtain.irina.moviedbkt.view.base.content.ContentView

/**
 * Created by Irina Shtain on 19.02.2018.
 */
interface MovieDetailsContract {
    interface View : ContentView {
        fun setupUI(movieItem: MovieItem)
        fun showReviews()
        fun showRatingDialog()
        fun getMovieID(): Int
    }

    interface Presenter : IBasePresenter<View> {
        fun fabRatingClicked()
        fun fabAddToFavoriteClicked()
        fun fabAddToWatchListClicked()
        fun fabAddToListClicked(listID: Int)
        fun showResult(errorCode: Int)
        fun menuReviewsPressed()
    }

    interface Model {
        fun addToListMovie(listID: Int, movieID: Int): Observable<ResponseMessage>
        fun getMovieDetails(movieID: Int): Observable<MovieItem>
        fun addToFavoriteMovie(movieID: Int): Observable<ResponseMessage>
        fun addToWatchListMovie(movieID: Int): Observable<ResponseMessage>
    }
}