package ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.movies_in_list

import io.reactivex.Observable
import ua.shtain.irina.moviedbkt.model.lists.ResponseMessage
import ua.shtain.irina.moviedbkt.model.movie.MovieItem
import ua.shtain.irina.moviedbkt.model.movie.MoviesResponse
import ua.shtain.irina.moviedbkt.view.base.refresheble_content.RefreshablePresenter
import ua.shtain.irina.moviedbkt.view.base.refresheble_content.RefreshableView
import ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.movies_in_list.adapter.MovieItemDH


/**
 * Created by Irina Shtain on 16.02.2018.
 */
interface MoviesInListContract {

    interface View : RefreshableView {
        fun setLists(itemDHS: MutableList<MovieItemDH>)
        fun openMovieDetails(movieID: Int)
        fun openSearchByTitleScreen(listID: Int)
        fun openSearchByGenreScreen(listID: Int)
        fun openLatestSearchScreen(listID: Int)
        fun openPopularSearchScreen(listID: Int)
        fun updateMovies(position: Int)
        fun closeFabMenu()
        fun openFabMenu()
        fun getListID(): Int
        fun showConfirmAlert(movieID: Int, position: Int)
    }

    interface Presenter : RefreshablePresenter {
        fun showDetails(movieID: Int)
        fun deleteMovie(movieID: Int, position: Int)
        fun deleteMovieAlert(movieID: Int, position: Int)
        fun onMainFABClick()
        fun onFabFindUsingTitleClick()
        fun onFabFindUsingGenreClick()
        fun onFabFindPopularClick()
        fun onFabFindLatestClick()
    }

    interface Model {
        fun getMovies(listID: Int): Observable<MoviesResponse>
        fun deleteMovie(listID: Int, movieID: Int): Observable<ResponseMessage>
    }
}