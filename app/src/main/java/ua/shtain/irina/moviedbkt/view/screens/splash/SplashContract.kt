package ua.shtain.irina.moviedbkt.view.screens.splash

import ua.shtain.irina.moviedbkt.view.base.IBasePresenter
import ua.shtain.irina.moviedbkt.view.base.IBaseView

/**
 * Created by Irina Shtain on 30.01.2018.
 */

interface SplashContract {

    interface SplashView : IBaseView {
        fun runSplashAnimation()

        fun startHomeScreen()

        fun startLoginScreen()
    }

    interface SplashPresenter : IBasePresenter<SplashView> {
        fun startNextScreen()
    }
}