package ua.shtain.irina.moviedbkt.view.screens.home.settings

import ua.shtain.irina.moviedbkt.view.base.IBasePresenter
import ua.shtain.irina.moviedbkt.view.base.content.ContentView

/**
 * Created by Irina Shtain on 26.03.2018.
 */
interface SettingsContract {
    interface View : ContentView {
        fun showAlertAboutLogout()
        fun openLogin()
    }

    interface Presenter : IBasePresenter<View> {
        fun logoutPressed()
        fun clearUser()
    }

    interface Model {

    }
}