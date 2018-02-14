package ua.shtain.irina.moviedbkt.view.screens.splash

import ua.shtain.irina.moviedbkt.root.session.ISessionManager
import javax.inject.Inject


/**
 * Created by Irina Shtain on 31.01.2018.
 */
class SplashPresenter @Inject constructor(sessionManager: ISessionManager) : SplashContract.SplashPresenter {

    lateinit var mView: SplashContract.SplashView
    var mSessionManager = sessionManager

    override fun setView(view: SplashContract.SplashView) {
        mView = view
    }

    override fun startNextScreen() {
        if (mSessionManager.getSessionID().isEmpty())
            mView.startLoginScreen()
        else
            mView.startHomeScreen()

    }


    override fun subscribe() {
        mView.runSplashAnimation()
    }

    override fun unsubscribe() {

    }

}
