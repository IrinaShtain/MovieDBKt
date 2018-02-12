package ua.shtain.irina.moviedbkt.view.screens.splash.di

import dagger.Module
import dagger.Provides
import ua.shtain.irina.moviedbkt.root.session.SessionManager
import ua.shtain.irina.moviedbkt.view.screens.splash.SplashContract
import ua.shtain.irina.moviedbkt.view.screens.splash.SplashPresenter

/**
 * Created by Irina Shtain on 31.01.2018.
 */
@Module
class DiSplashModule {

    @Provides
    @SplashScope
    fun provideSplashPresenter(sessionManager : SessionManager): SplashContract.SplashPresenter = SplashPresenter(sessionManager)

}