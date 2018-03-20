package ua.shtain.irina.moviedbkt.domain

import io.reactivex.Observable
import ua.shtain.irina.moviedbkt.model.lists.ResponseMessage
import ua.shtain.irina.moviedbkt.model.movie.*
import ua.shtain.irina.moviedbkt.model.movie.review.ReviewResponse
import ua.shtain.irina.moviedbkt.model.requests.FavoriteRequest
import ua.shtain.irina.moviedbkt.model.requests.RateRequest
import ua.shtain.irina.moviedbkt.model.requests.WatchRequest
import ua.shtain.irina.moviedbkt.root.network.servises.MovieService
import ua.shtain.irina.moviedbkt.root.rx.SchedulerHelper
import ua.shtain.irina.moviedbkt.view.screens.home.common.movie_details.MovieDetailsContract
import ua.shtain.irina.moviedbkt.view.screens.home.common.movie_details.reviews.ReviewsContract
import ua.shtain.irina.moviedbkt.view.screens.home.common.rating_dialog.RatingDialogContract
import ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.movies_in_list.MoviesInListContract
import ua.shtain.irina.moviedbkt.view.screens.home.common.movies.MoviesContract
import javax.inject.Inject

/**
 * Created by Irina Shtain on 19.02.2018.
 */
class MovieRepository @Inject constructor(movieService: MovieService, helper: SchedulerHelper) : MoviesInListContract.Model,
        MovieDetailsContract.Model, MoviesContract.Model, RatingDialogContract.Model, ReviewsContract.Model {
    val mService = movieService
    val mHelper = helper

    override fun getMovies(listID: Int): Observable<MoviesResponse> = mHelper.getNetworkObservable(mService.getMovies(listID))


    override fun addToListMovie(listID: Int, movieID: Int) = mHelper.getNetworkObservable(mService.addMovie(listID, ActionRequest(movieID)))

    override fun deleteMovie(listID: Int, movieID: Int) = mHelper.getNetworkObservable(mService.deleteMovie(listID, ActionRequest(movieID)))

    override fun getMovieDetails(movieID: Int) = mHelper.getNetworkObservable(mService.getMovieDetails(movieID))

    override fun addToFavoriteMovie(movieID: Int) = mHelper.getNetworkObservable(mService.markToFavoriteMovie(FavoriteRequest("movie", movieID, true)))

    override fun addToWatchListMovie(movieID: Int) = mHelper.getNetworkObservable(mService.markToWatchListMovie(WatchRequest("movie", movieID, true)))

    override fun deleteFromFavoriteMovies(movieID: Int) = mHelper.getNetworkObservable(mService.markToFavoriteMovie(FavoriteRequest("movie", movieID, false)))

    override fun deleteFromWatchListMovies(movieID: Int) = mHelper.getNetworkObservable(mService.markToWatchListMovie(WatchRequest("movie", movieID, false)))

    override fun getGenres() = mHelper.getNetworkObservable(mService.getGenres())

    override fun searchMoviesByTitle(title: String, page: Int) = mHelper.getNetworkObservable(mService.searchMovieByTitle(title, page))

    override fun searchMovieByGenre(genreId: Int, page: Int) = mHelper.getNetworkObservable(mService.searchMovieByGenre(genreId, page))

    override fun searchLatestMovies(page: Int) = mHelper.getNetworkObservable(mService.searchLatestMovies(page))

    override fun searchPopularMovies(page: Int) = mHelper.getNetworkObservable(mService.searchPopularMovies(page))

    override fun getFavoriteMovies(page: Int) = mHelper.getNetworkObservable(mService.getFavoriteMovies(page))

    override fun getWatchlistMovies(page: Int) = mHelper.getNetworkObservable(mService.getWatchlistMovies(page))

    override fun rateMovie(rating: Float, movieID: Int) = mHelper.getNetworkObservable(mService.rateMovie(movieID, RateRequest(rating)))

    override fun rateTV(rating: Float, tvID: Int) = mHelper.getNetworkObservable(mService.rateTv(tvID, RateRequest(rating)))

    override fun getReviews(movieID: Int, page: Int) = mHelper.getNetworkObservable(mService.getReviews(movieID, page))
}