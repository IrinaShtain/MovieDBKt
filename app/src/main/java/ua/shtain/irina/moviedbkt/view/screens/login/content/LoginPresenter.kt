package ua.shtain.irina.moviedbkt.view.screens.login.content

import android.util.Log
import io.reactivex.disposables.CompositeDisposable
import ua.shtain.irina.moviedbkt.model.exceptions.ConnectionException
import ua.shtain.irina.moviedbkt.other.Constants
import ua.shtain.irina.moviedbkt.root.session.SessionManager
import javax.inject.Inject

/**
 * Created by Irina Shtain on 09.02.2018.
 */
class LoginPresenter @Inject constructor(sessionManager: SessionManager,
                                         compositeDisposable: CompositeDisposable,
                                         model: LoginContract.LoginModel) : LoginContract.LoginPresenter {

    lateinit var mView: LoginContract.LoginView

    private var mSessionManager = sessionManager
    private var mCompositeDisposable = compositeDisposable
    private var mModel = model


    private val throwableConsumer = { t:Throwable ->
        Log.d("myLogs", "Error! " + t.message)
        mView.hideProgress()
        if (t is ConnectionException)
            mView.showMessage(Constants.MessageType.CONNECTION_PROBLEMS)
        else
            mView.showMessage(Constants.MessageType.UNKNOWN)
        t.printStackTrace()
    }

    override fun setView(view: LoginContract.LoginView) {
        mView = view
    }

    override fun subscribe() {
        mView.showProgressMain()
        mCompositeDisposable.add(mModel.getToken()
                .subscribe({ loginResponse ->
                    mView.hideProgress()
                    Log.e("myLog", "loginResponse.data.token.auth_token " + loginResponse.requestToken)
                    mSessionManager.saveAuthToken(loginResponse.requestToken)
                }, throwableConsumer))
    }

    override fun unsubscribe() {
        mCompositeDisposable.clear()
        Log.e("myLog", "LoginPresenter unsubscribe()")
    }

    override fun onSignInClick(userName: String, password: String) {
        var hasPassword = false
        var hasEmail = false

        if (userName.isEmpty())
            mView.showEmptyNameError()
        else {
            hasEmail = true
        }

        if (password.isEmpty()) {
            mView.showEmptyPasswordError()
        } else {
            if (!validatePassword(password))
                mView.showNotValidPasswordError()
            else
                hasPassword = true
        }

        if (hasEmail && hasPassword) {
            mView.setPasswordErrorEnabled(false)
            mView.setUserNameErrorEnabled(false)
            getSessionID(userName, password)
        }

        if (hasEmail)
            mView.setUserNameErrorEnabled(false)

        if (hasPassword)
            mView.setPasswordErrorEnabled(false)
    }

    private fun getSessionID(userName: String, password: String) {
        mView.showProgressMain()
        if (!mSessionManager.getAccessToken().isEmpty())
            mCompositeDisposable.add(
                    mModel.getSessionID(userName, password, mSessionManager.getAccessToken())
                            .subscribe({ loginResponse ->
                                mView.hideProgress()
                                mSessionManager.saveSessionID(loginResponse.sessionID)
                                Log.e("myLog", "getSessionID .sessionID " + loginResponse.sessionID)
                                mView.startHomeScreen()
                            }, throwableConsumer))
        else {
            subscribe()
        }

    }


    private fun validatePassword(password: String) = password.length >= 4
}