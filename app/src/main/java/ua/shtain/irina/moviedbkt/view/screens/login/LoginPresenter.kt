package ua.shtain.irina.moviedbkt.view.screens.login

import ua.shtain.irina.moviedbkt.root.session.SharedPrefManager
import ua.shtain.irina.moviedbkt.view.screens.splash.SplashContract
import javax.inject.Inject

/**
 * Created by Irina Shtain on 09.02.2018.
 */
class LoginPresenter @Inject constructor(sessionManager: SharedPrefManager) : LoginContract.LoginPresenter{

    lateinit var mView: LoginContract.LoginView
    var mSessionManager: SharedPrefManager = sessionManager
    override fun subscribe() {

    }

    override fun unsubscribe() {

    }

    override fun onSignInClick(userName: String, password: String) {
        mView.showEmptyNameError()
    }

    override fun setView(view: LoginContract.LoginView) {
        mView = view
    }

}