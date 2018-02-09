package ua.shtain.irina.moviedbkt.view.screens.login

import io.reactivex.Observable
import ua.shtain.irina.moviedbkt.view.base.IBasePresenter
import ua.shtain.irina.moviedbkt.view.base.IBaseView

/**
 * Created by Irina Shtain on 09.02.2018.
 */
interface LoginContract {

    interface LoginView : IBaseView {
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
//        fun getToken: Observable<LoginToken>
//        fun getSessionID(validatedToken: String): Observable<LoginSession>
 //       fun getValidatedToken(username: String, password: String, request_token: String): Observable<LoginToken>
    }
}