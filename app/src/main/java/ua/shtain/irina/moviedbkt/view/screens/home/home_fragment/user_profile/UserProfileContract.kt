package ua.shtain.irina.moviedbkt.view.screens.home.home_fragment.user_profile

import io.reactivex.Observable
import ua.shtain.irina.moviedbkt.model.user.User
import ua.shtain.irina.moviedbkt.view.base.IBasePresenter
import ua.shtain.irina.moviedbkt.view.base.content.ContentView

/**
 * Created by Irina Shtain on 13.02.2018.
 */
interface UserProfileContract {

    interface View : ContentView {
        fun setUserNick(name: String)
        fun setUserName(name: String)
        fun setAdultPermission(hasPermission: Boolean)

    }

    interface Presenter : IBasePresenter<View> {

    }

    interface Model {
        fun getUserDetails(sessionID: String): Observable<User>
    }
}