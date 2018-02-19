package ua.shtain.irina.moviedbkt.domain

import io.reactivex.Observable
import ua.shtain.irina.moviedbkt.model.lists.ResponseMessage
import ua.shtain.irina.moviedbkt.model.movie.MoviesResponse
import ua.shtain.irina.moviedbkt.root.network.servises.MovieService
import ua.shtain.irina.moviedbkt.root.rx.SchedulerHelper
import ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.movies_in_list.MoviesInListContract
import javax.inject.Inject

/**
 * Created by Irina Shtain on 19.02.2018.
 */
class MovieRepository @Inject constructor(movieService: MovieService, helper: SchedulerHelper) : MoviesInListContract.Model {
    val mService = movieService
    val mHelper = helper

    override fun getMovies(listID: Int): Observable<MoviesResponse> = mHelper.getNetworkObservable(mService.getMovies(listID))

    override fun deleteList(listID: Int): Observable<ResponseMessage> = mHelper.getNetworkObservable(mService.deleteList(listID))

}