package ua.shtain.irina.moviedbkt.domain

import ua.shtain.irina.moviedbkt.model.movie.FavoriteRequest
import ua.shtain.irina.moviedbkt.model.movie.WatchRequest
import ua.shtain.irina.moviedbkt.root.network.servises.TvService
import ua.shtain.irina.moviedbkt.root.rx.SchedulerHelper
import ua.shtain.irina.moviedbkt.view.screens.home.common.tv_show_details.TVDetailsContract
import javax.inject.Inject

/**
 * Created by Irina Shtain on 01.03.2018.
 */
class TVRepository @Inject constructor(tvService: TvService, helper: SchedulerHelper) : TVDetailsContract.Model {
    val mService = tvService
    val mHelper = helper

    override fun getTvDetails(tvID: Int) = mHelper.getNetworkObservable(mService.getTvDetails(tvID))

    override fun addToFavoriteTV(movieID: Int) = mHelper.getNetworkObservable(mService.addToFavoriteMovie(FavoriteRequest("tv", movieID, true)))

    override fun addToWatchListTV(movieID: Int) = mHelper.getNetworkObservable(mService.addToWatchListMovie(WatchRequest("tv", movieID, true)))
}