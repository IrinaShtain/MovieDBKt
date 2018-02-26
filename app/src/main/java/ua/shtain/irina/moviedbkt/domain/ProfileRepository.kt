package ua.shtain.irina.moviedbkt.domain

import io.reactivex.Observable
import ua.shtain.irina.moviedbkt.model.lists.CreatedListsData
import ua.shtain.irina.moviedbkt.model.lists.NewListRequest
import ua.shtain.irina.moviedbkt.model.lists.ResponseMessage
import ua.shtain.irina.moviedbkt.model.user.User
import ua.shtain.irina.moviedbkt.root.network.servises.ProfileService
import ua.shtain.irina.moviedbkt.root.rx.SchedulerHelper
import ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.MovieListsContract
import ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.add_list.CreateNewListContract
import ua.shtain.irina.moviedbkt.view.screens.home.home_fragment.user_profile.UserProfileContract
import javax.inject.Inject

/**
 * Created by Irina Shtain on 13.02.2018.
 */
class ProfileRepository @Inject constructor(profileService: ProfileService, helper: SchedulerHelper) : UserProfileContract.Model,
        MovieListsContract.Model, CreateNewListContract.Model {
    val mService = profileService
    val mHelper = helper
    override fun getUserDetails(sessionID: String): Observable<User> = mHelper.getNetworkObservable(mService.getDetails(sessionID))

    override fun getLists(userID: Int, page: Int): Observable<CreatedListsData> = mHelper.getNetworkObservable(mService.getLists(userID, page))

    override fun deleteList(listID: Int): Observable<ResponseMessage> = mHelper.getNetworkObservable(mService.deleteList(listID))

    override fun createList(listTitle: String, listDesc: String) = mHelper.getNetworkObservable(mService.createList(NewListRequest(listTitle, listDesc)))
}