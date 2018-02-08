package ua.shtain.irina.moviedbkt.view.screens.splash

import javax.inject.Inject


/**
 * Created by Irina Shtain on 31.01.2018.
 */
class SplashPresenter @Inject constructor() : SplashContract.SplashPresenter {
    lateinit var mView: SplashContract.SplashView

    override fun setView(view: SplashContract.SplashView) {
        mView = view
    }

    override fun startNextScreen() {
    }


    override fun subscribe() {
        mView.runSplashAnimation()
    }

    override fun unsubscribe() {

    }
}
