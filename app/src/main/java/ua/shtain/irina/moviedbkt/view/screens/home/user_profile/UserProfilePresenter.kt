package ua.shtain.irina.moviedbkt.view.screens.home.user_profile

import android.util.Log
import io.reactivex.disposables.CompositeDisposable
import ua.shtain.irina.moviedbkt.model.exceptions.ConnectionException
import ua.shtain.irina.moviedbkt.model.main.User
import ua.shtain.irina.moviedbkt.other.Constants
import ua.shtain.irina.moviedbkt.root.session.ISessionManager
import javax.inject.Inject

/**
 * Created by Irina Shtain on 13.02.2018.
 */
class UserProfilePresenter @Inject constructor(sessionManager: ISessionManager,
                                               compositeDisposable: CompositeDisposable,
                                               model: UserProfileContract.Model) : UserProfileContract.Presenter {
    lateinit var mView: UserProfileContract.View

    private var mSessionManager = sessionManager
    private var mCompositeDisposable = compositeDisposable
    private var mModel = model

    private val throwableConsumer = { t: Throwable ->
        Log.d("myLogs", "Error! " + t.message)
        mView.hideProgress()
        if (t is ConnectionException)
            mView.showMessage(Constants.MessageType.CONNECTION_PROBLEMS)
        else
            mView.showMessage(Constants.MessageType.UNKNOWN)
        t.printStackTrace()
    }


    override fun setView(view: UserProfileContract.View) {
        mView = view
    }

    override fun subscribe() {
        val currentUser = mSessionManager.getUserData()
        if (currentUser.id == -1) {
            mView.showProgressMain()
            mCompositeDisposable.addAll(mModel.getUserDetails(mSessionManager.getSessionID()).subscribe(
                    { user ->
                        mView.hideProgress()
                        mSessionManager.saveUserData(user)
                        displayUserData(user)
                    }, throwableConsumer
            ))
        } else
            displayUserData(currentUser)
    }

    private fun displayUserData(user: User) {
        mView.setUserNick(user.username)
        mView.setUserName(user.name)
        mView.setAdultPermission(user.includeAdult)
    }

    override fun unsubscribe() {
        mCompositeDisposable.clear()
    }

    override fun menuPressed() {
        mView.showAlertAboutLogout()
    }

    override fun clearUser() {
        mSessionManager.deleteUserData()
    }
}