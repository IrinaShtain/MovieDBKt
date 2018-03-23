package ua.shtain.irina.moviedbkt.domain

import io.reactivex.Observable
import ua.shtain.irina.moviedbkt.model.requests.FavoriteRequest
import ua.shtain.irina.moviedbkt.model.requests.WatchRequest
import ua.shtain.irina.moviedbkt.model.tv.SearchTvShowResponse
import ua.shtain.irina.moviedbkt.root.network.servises.TvService
import ua.shtain.irina.moviedbkt.root.rx.SchedulerHelper
import ua.shtain.irina.moviedbkt.view.screens.home.common.tv_show_details.TvShowDetailsContract
import ua.shtain.irina.moviedbkt.view.screens.home.common.tv_shows.TvShowsContract
import javax.inject.Inject

/**
 * Created by Irina Shtain on 01.03.2018.
 */
class TVRepository @Inject constructor(tvService: TvService, helper: SchedulerHelper) : TvShowDetailsContract.Model, TvShowsContract.Model {
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

}