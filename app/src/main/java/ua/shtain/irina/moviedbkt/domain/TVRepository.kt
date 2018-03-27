package ua.shtain.irina.moviedbkt.domain

import io.reactivex.Observable
import ua.shtain.irina.moviedbkt.model.movie.review.ReviewResponse
import ua.shtain.irina.moviedbkt.model.movie.videos.VideosResponse
import ua.shtain.irina.moviedbkt.model.requests.FavoriteRequest
import ua.shtain.irina.moviedbkt.model.requests.WatchRequest
import ua.shtain.irina.moviedbkt.model.tv.SearchTvShowResponse
import ua.shtain.irina.moviedbkt.root.network.servises.TvService
import ua.shtain.irina.moviedbkt.root.rx.SchedulerHelper
import ua.shtain.irina.moviedbkt.view.screens.home.common.reviews.ReviewsContract
import ua.shtain.irina.moviedbkt.view.screens.home.common.tv_show_details.TvShowDetailsContract
import ua.shtain.irina.moviedbkt.view.screens.home.common.tv_shows.TvShowsContract
import ua.shtain.irina.moviedbkt.view.screens.home.common.videos.VideosContract
import javax.inject.Inject

/**
 * Created by Irina Shtain on 01.03.2018.
 */
class TVRepository @Inject constructor(tvService: TvService, helper: SchedulerHelper) : TvShowDetailsContract.Model, TvShowsContract.Model,
        ReviewsContract.Model, VideosContract.Model {
    val mService = tvService
    val mHelper = helper

    override fun getTvDetails(tvID: Int) = mHelper.getNetworkObservable(mService.getTvDetails(tvID))

    override fun addToFavoriteTV(showID: Int) = mHelper.getNetworkObservable(mService.markToFavoriteMovie(FavoriteRequest("tv", showID, true)))

    override fun addToWatchListTV(showID: Int) = mHelper.getNetworkObservable(mService.markToWatchListMovie(WatchRequest("tv", showID, true)))

    override fun searchTopRatedShows(page: Int) = mHelper.getNetworkObservable(mService.searchTopRatedShows(page))

    override fun searchTvOnTheAir(page: Int) = mHelper.getNetworkObservable(mService.searchTvOnTheAir(page))

    override fun searchLatestShows(page: Int) = mHelper.getNetworkObservable(mService.searchLatestShows(page))

    override fun searchPopularShows(page: Int) = mHelper.getNetworkObservable(mService.searchPopularShows(page))

    override fun getFavoriteShows(page: Int) = mHelper.getNetworkObservable(mService.getFavoriteaShows(page))

    override fun getWatchlistShows(page: Int) = mHelper.getNetworkObservable(mService.getWatchlistShows(page))

    override fun deleteFromFavoriteTV(showID: Int) = mHelper.getNetworkObservable(mService.markToFavoriteMovie(FavoriteRequest("tv", showID, false)))

    override fun deleteFromWatchListTV(showID: Int) = mHelper.getNetworkObservable(mService.markToWatchListMovie(WatchRequest("tv", showID, false)))

    override fun getRecommendedShows(showID: Int, page: Int) = mHelper.getNetworkObservable(mService.getTvShowRecommendations(showID, page))

    override fun getMovieReviews(movieID: Int, page: Int): Observable<ReviewResponse> {
        return Observable.empty() //ignored
    }

    override fun getTvShowsReviews(showID: Int, page: Int) = mHelper.getNetworkObservable(mService.getTvShowReviews(showID, page))

    override fun getMovieVideos(movieID: Int): Observable<VideosResponse> {
        return Observable.empty() //ignored
    }

    override fun getTvshowVideos(tvShowID: Int) = mHelper.getNetworkObservable(mService.getTvShowVideos(tvShowID))
}