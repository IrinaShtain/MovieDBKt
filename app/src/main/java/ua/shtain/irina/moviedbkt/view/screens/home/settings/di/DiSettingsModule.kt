package ua.shtain.irina.moviedbkt.view.screens.home.settings.di

import dagger.Module
import dagger.Provides
import ua.shtain.irina.moviedbkt.root.session.SessionManager
import ua.shtain.irina.moviedbkt.view.screens.home.di.MainScope
import ua.shtain.irina.moviedbkt.view.screens.home.settings.SettingsContract
import ua.shtain.irina.moviedbkt.view.screens.home.settings.SettingsPresenter

/**
 * Created by Irina Shtain on 26.03.2018.
 */
@Module
class DiSettingsModule {
    @Provides
    @MainScope
    fun provideSettingsPresenter(sessionManager: SessionManager)
            : SettingsContract.Presenter = SettingsPresenter(sessionManager)
}