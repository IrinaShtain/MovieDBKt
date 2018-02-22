package ua.shtain.irina.moviedbkt.domain

import io.reactivex.Observable
import ua.shtain.irina.moviedbkt.model.lists.ResponseMessage
import ua.shtain.irina.moviedbkt.model.movie.*
import ua.shtain.irina.moviedbkt.model.movie.genre.GenresResponse
import ua.shtain.irina.moviedbkt.root.network.servises.MovieService
import ua.shtain.irina.moviedbkt.root.rx.SchedulerHelper
import ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.movie_details.MovieDetailsContract
import ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.movies_in_list.MoviesInListContract
import ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.search.SearchMovieContract
import javax.inject.Inject

/**
 * Created by Irina Shtain on 19.02.2018.
 */
class MovieRepository @Inject constructor(movieService: MovieService, helper: SchedulerHelper) : MoviesInListContract.Model,
        MovieDetailsContract.Model, SearchMovieContract.Model {
    val mService = movieService
    val mHelper = helper

    override fun getMovies(listID: Int): Observable<MoviesResponse> = mHelper.getNetworkObservable(mService.getMovies(listID))


    override fun addToListMovie(listID: Int, movieID: Int) = mHelper.getNetworkObservable(mService.addMovie(listID, ActionRequest(movieID)))

    override fun deleteMovie(listID: Int, movieID: Int) = mHelper.getNetworkObservable(mService.deleteMovie(listID, ActionRequest(movieID)))

    override fun getMovieDetails(movieID: Int) = mHelper.getNetworkObservable(mService.getMovieDetails(movieID))

    override fun addToFavoriteMovie(movieID: Int) = mHelper.getNetworkObservable(mService.addToFavoriteMovie(FavoriteRequest("movie", movieID, true)))

    override fun addToWatchListMovie(movieID: Int) = mHelper.getNetworkObservable(mService.addToWatchListMovie(WatchRequest("movie", movieID, true)))

    override fun getGenres() = mHelper.getNetworkObservable(mService.getGenres())

    override fun getMoviesByTitle(title: String, page: Int) = mHelper.getNetworkObservable(mService.searchMovieByTitle(title, page))

    override fun searchMovieByGenre(genreId: Int, page: Int) = mHelper.getNetworkObservable(mService.searchMovieByGenre(genreId, page))

    override fun getLatestMovies(page: Int) = mHelper.getNetworkObservable(mService.searchLatestMovies(page))

    override fun getPopularMovies(page: Int) = mHelper.getNetworkObservable(mService.searchPopularMovies(page))
}