package ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.search

import io.reactivex.Observable
import ua.shtain.irina.moviedbkt.model.movie.SearchMovieResponse
import ua.shtain.irina.moviedbkt.model.movie.genre.GenresResponse
import ua.shtain.irina.moviedbkt.view.base.refresheble_content.RefreshablePresenter
import ua.shtain.irina.moviedbkt.view.base.refresheble_content.RefreshableView
import ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.movies_in_list.adapter.MovieItemDH
import ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.search.genre_adapter.GenreDH
import java.util.ArrayList

/**
 * Created by Irina Shtain on 20.02.2018.
 */
interface SearchMovieContract {
    interface View : RefreshableView {
        fun setList(movieDHs: MutableList<MovieItemDH>)
        fun addList(movieDHs: MutableList<MovieItemDH>)
        fun setGenres(genreItems: ArrayList<GenreDH>)
        fun setupSearchByTitle()
        fun setupGenresList()
        fun clearListData()
        fun getSearchType(): Int
    }

    interface Presenter : RefreshablePresenter {
        fun onSearchClick(movieTitle: String)
        fun getNextPage()
        fun searchByGenre(id: Int)
        fun updateNeedRefresh(needRefresh: Boolean)
    }

    interface Model {
        fun getGenres(): Observable<GenresResponse>
        fun getMoviesByTitle(title: String, page: Int): Observable<SearchMovieResponse>
        fun searchMovieByGenre(genreId: Int, page: Int): Observable<SearchMovieResponse>
        fun getLatestMovies(page: Int): Observable<SearchMovieResponse>
        fun getPopularMovies(page: Int): Observable<SearchMovieResponse>
    }
}