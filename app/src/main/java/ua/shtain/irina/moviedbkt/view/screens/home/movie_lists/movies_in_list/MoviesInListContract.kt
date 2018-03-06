package ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.movies_in_list

import io.reactivex.Observable
import ua.shtain.irina.moviedbkt.model.lists.ResponseMessage
import ua.shtain.irina.moviedbkt.model.movie.MoviesResponse
import ua.shtain.irina.moviedbkt.view.base.refresheble_content.RefreshablePresenter
import ua.shtain.irina.moviedbkt.view.base.refresheble_content.RefreshableView
import ua.shtain.irina.moviedbkt.view.screens.home.common.movies.adapter.MovieItemDH


/**
 * Created by Irina Shtain on 16.02.2018.
 */
interface MoviesInListContract {

    interface View : RefreshableView {
        fun setLists(itemDHS: MutableList<MovieItemDH>)
        fun getListID(): Int
        fun showConfirmAlert(movieID: Int, position: Int)
        fun updateMovies(position: Int)
    }

    interface Presenter : RefreshablePresenter {
        fun showedDetails()
        fun deleteMovie(movieID: Int, position: Int)
        fun deleteMovieAlert(movieID: Int, position: Int)
    }

    interface Model {
        fun getMovies(listID: Int): Observable<MoviesResponse>
        fun deleteMovie(listID: Int, movieID: Int): Observable<ResponseMessage>
    }
}