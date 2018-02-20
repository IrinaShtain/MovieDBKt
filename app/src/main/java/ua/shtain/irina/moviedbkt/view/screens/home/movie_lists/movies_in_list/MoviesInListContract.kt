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
        fun setLists(itemDHS: ArrayList<MovieItemDH>)
        fun openMovieDetails(movieID: Int)
        fun openSearchByTitleScreen(listID: Int, movieItems: ArrayList<MovieItem>)
        fun openSearchByGenreScreen(listID: Int, movieItems: ArrayList<MovieItem>)
        fun openLatestSearchScreen(listID: Int, movieItems: ArrayList<MovieItem>)
        fun openPopularSearchScreen(listID: Int, movieItems: ArrayList<MovieItem>)
        fun closeFragment()
        fun showAlert()
        fun closeFabMenu()
        fun openFabMenu()
        fun getListID(): Int
    }

    interface Presenter : RefreshablePresenter {
        fun showDetails(movieID: Int)
        fun deleteList(listID: Int)
        fun onMainFABClick()
        fun menuPressed()
        fun onFabFindUsingTitleClick()
        fun onFabFindUsingGenreClick()
        fun onFabFindPopularClick()
        fun onFabFindLatestClick()
    }

    interface Model {
        fun getMovies(listID: Int): Observable<MoviesResponse>
        fun deleteList(listID: Int): Observable<ResponseMessage>
    }
}