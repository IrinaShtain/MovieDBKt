package ua.shtain.irina.moviedbkt.view.screens.home.common.movies

import io.reactivex.Observable
import ua.shtain.irina.moviedbkt.model.movie.SearchMovieResponse
import ua.shtain.irina.moviedbkt.model.genre.GenresResponse
import ua.shtain.irina.moviedbkt.model.lists.ResponseMessage
import ua.shtain.irina.moviedbkt.view.base.refresheble_content.RefreshablePresenter
import ua.shtain.irina.moviedbkt.view.base.refresheble_content.RefreshableView
import ua.shtain.irina.moviedbkt.view.screens.home.common.movies.adapter.MovieItemDH
import ua.shtain.irina.moviedbkt.view.screens.home.common.search_movies.genre_adapter.GenreDH
import java.util.ArrayList

/**
 * Created by Irina Shtain on 20.02.2018.
 */
interface MoviesContract {
    interface View : RefreshableView {
        fun setList(movieDHs: MutableList<MovieItemDH>)
        fun addList(movieDHs: MutableList<MovieItemDH>)
        fun setGenres(genreItems: ArrayList<GenreDH>)
        fun setupSearchByTitle()
        fun setupGenresList()
        fun getSearchType(): Int
        fun showAlert(itemId: Int, position: Int)
        fun updateMovies(position: Int)
    }

    interface Presenter : RefreshablePresenter {
        fun onSearchClick(movieTitle: String)
        fun getNextPage()
        fun searchByGenre(id: Int)
        fun updateNeedRefresh(needRefresh: Boolean)
        fun deletionConfirmed(itemId: Int, position: Int)
        fun deleteMovie(movieID: Int, position: Int)
    }

    interface Model {
        fun getGenres(): Observable<GenresResponse>
        fun searchMoviesByTitle(title: String, page: Int): Observable<SearchMovieResponse>
        fun searchMovieByGenre(genreId: Int, page: Int): Observable<SearchMovieResponse>
        fun searchLatestMovies(page: Int): Observable<SearchMovieResponse>
        fun searchPopularMovies(page: Int): Observable<SearchMovieResponse>
        fun getFavoriteMovies(page: Int): Observable<SearchMovieResponse>
        fun getWatchlistMovies(page: Int): Observable<SearchMovieResponse>
        fun deleteFromFavoriteMovies(movieID: Int): Observable<ResponseMessage>
        fun deleteFromWatchListMovies(movieID: Int): Observable<ResponseMessage>
    }
}