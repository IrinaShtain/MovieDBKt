package ua.shtain.irina.moviedbkt.domain

import ua.shtain.irina.moviedbkt.root.network.servises.StarService
import ua.shtain.irina.moviedbkt.root.rx.SchedulerHelper
import ua.shtain.irina.moviedbkt.view.screens.home.stars.SearchStarContract
import ua.shtain.irina.moviedbkt.view.screens.home.stars.stars_details.StarsDetailsContract
import javax.inject.Inject

/**
 * Created by Irina Shtain on 01.03.2018.
 */
class StarRepository @Inject constructor(service: StarService, helper: SchedulerHelper) : SearchStarContract.Model, StarsDetailsContract.Model {
    val mService = service
    val mHelper = helper

    override fun getStars(name: String, page: Int) = mHelper.getNetworkObservable(mService.searchStar(name, page))

    override fun getStarDetails(personID: Int)= mHelper.getNetworkObservable(mService.getStarDetails(personID))
}