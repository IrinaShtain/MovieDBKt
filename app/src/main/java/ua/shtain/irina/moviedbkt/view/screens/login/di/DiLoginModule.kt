package ua.shtain.irina.moviedbkt.view.screens.login.di

import dagger.Module
import dagger.Provides
import ua.shtain.irina.moviedbkt.root.session.SharedPrefManager
import ua.shtain.irina.moviedbkt.view.screens.splash.SplashContract
import ua.shtain.irina.moviedbkt.view.screens.splash.SplashPresenter
import ua.shtain.irina.moviedbkt.view.screens.splash.di.SplashScope

/**
 * Created by Irina Shtain on 09.02.2018.
 */
@Module
class DiLoginModule {

    @Provides
    @SplashScope
    fun provideSplashPresenter(sessionManager : SharedPrefManager): SplashContract.SplashPresenter = SplashPresenter(sessionManager)

}