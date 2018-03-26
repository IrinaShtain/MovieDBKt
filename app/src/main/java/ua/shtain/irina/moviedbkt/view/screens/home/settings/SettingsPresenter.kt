package ua.shtain.irina.moviedbkt.view.screens.home.settings

import ua.shtain.irina.moviedbkt.root.session.ISessionManager
import javax.inject.Inject

/**
 * Created by Irina Shtain on 26.03.2018.
 */
class SettingsPresenter @Inject constructor(sessionManager: ISessionManager) : SettingsContract.Presenter {

    lateinit var mView: SettingsContract.View

    private var mSessionManager = sessionManager

    override fun setView(view: SettingsContract.View) {
        mView = view
    }

    override fun subscribe() {

    }

    override fun unsubscribe() {

    }

    override fun logoutPressed() {
        mView.showAlertAboutLogout()
    }

    override fun clearUser() {
        mSessionManager.deleteUserData()
        mView.openLogin()
    }
}