package ua.shtain.irina.moviedbkt.domain

import ua.shtain.irina.moviedbkt.root.network.servises.StarService
import ua.shtain.irina.moviedbkt.root.rx.SchedulerHelper
import ua.shtain.irina.moviedbkt.view.screens.home.stars.SearchStarContract
import javax.inject.Inject

/**
 * Created by Irina Shtain on 01.03.2018.
 */
class StarRepository @Inject constructor(service: StarService, helper: SchedulerHelper) : SearchStarContract.Model {
    val mService = service
    val mHelper = helper

    override fun getStars(name: String, page: Int) = mHelper.getNetworkObservable(mService.searchStar(name, page))
}