package ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.movie_details

import io.reactivex.Observable
import retrofit2.http.Body
import ua.shtain.irina.moviedbkt.model.lists.ResponseMessage
import ua.shtain.irina.moviedbkt.model.movie.FavoriteRequest
import ua.shtain.irina.moviedbkt.model.movie.MovieItem
import ua.shtain.irina.moviedbkt.model.movie.RateRequest
import ua.shtain.irina.moviedbkt.view.base.IBasePresenter
import ua.shtain.irina.moviedbkt.view.base.content.ContentView

/**
 * Created by Irina Shtain on 19.02.2018.
 */
interface MovieDetailsContract {
    interface View : ContentView {
        fun setupButton(isMovieAdded: Boolean)
        fun setupUI(movieItem: MovieItem)
        fun showRatingDialog()
        fun getMovieID(): Int
    }

    interface Presenter : IBasePresenter<View> {
        fun fabRatingClicked()
        fun fabAddToFavoriteClicked()
        fun fabAddToWatchListClicked()
        fun fabAddToListClicked(listID: Int)
        fun showResult(errorCode: Int)
    }

    interface Model {
        fun addToListMovie(listID: Int, movieID: Int): Observable<ResponseMessage>
        fun deleteMovie(listID: Int, movieID: Int): Observable<ResponseMessage>
        fun getMovieDetails(movieID: Int): Observable<MovieItem>
        fun addToFavoriteMovie(movieID: Int): Observable<ResponseMessage>
        fun addToWatchListMovie(movieID: Int): Observable<ResponseMessage>
    }
}