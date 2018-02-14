package ua.shtain.irina.moviedbkt.domain

import io.reactivex.Observable
import ua.shtain.irina.moviedbkt.model.main.User
import ua.shtain.irina.moviedbkt.root.network.servises.ProfileService
import ua.shtain.irina.moviedbkt.root.rx.SchedulerHelper
import ua.shtain.irina.moviedbkt.view.screens.home.user_profile.UserProfileContract
import javax.inject.Inject

/**
 * Created by Irina Shtain on 13.02.2018.
 */
class ProfileRepository @Inject constructor(profileService: ProfileService, helper: SchedulerHelper) : UserProfileContract.Model {
    val mService = profileService
    val mHelper = helper
    override fun getUserDetails(sessionID: String): Observable<User> = mHelper.getNetworkObservable(mService.getDetails(sessionID))

}