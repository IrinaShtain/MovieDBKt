package ua.shtain.irina.moviedbkt.view.screens.login.content

import io.reactivex.Observable
import ua.shtain.irina.moviedbkt.model.user.LoginSession
import ua.shtain.irina.moviedbkt.model.user.LoginToken
import ua.shtain.irina.moviedbkt.view.base.IBasePresenter
import ua.shtain.irina.moviedbkt.view.base.content.ContentView

/**
 * Created by Irina Shtain on 09.02.2018.
 */
interface LoginContract {

    interface LoginView : ContentView {
        fun showEmptyNameError()
        fun showEmptyPasswordError()
        fun showNotValidPasswordError()
        fun setUserNameErrorEnabled(isEnabled: Boolean)
        fun setPasswordErrorEnabled(isEnabled: Boolean)

        fun startHomeScreen()
    }

    interface LoginPresenter : IBasePresenter<LoginView> {
        fun onSignInClick(userName: String, password: String)
    }

    interface LoginModel {
        fun getToken(): Observable<LoginToken>
        fun getSessionID(username: String, password: String, request_token: String): Observable<LoginSession>
    }
}