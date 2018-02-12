package ua.shtain.irina.moviedbkt.view.screens.login

import android.util.Log
import io.reactivex.disposables.CompositeDisposable
import ua.shtain.irina.moviedbkt.root.session.SessionManager
import javax.inject.Inject

/**
 * Created by Irina Shtain on 09.02.2018.
 */
class LoginPresenter @Inject constructor(sessionManager: SessionManager,
                                         compositeDisposable: CompositeDisposable,
                                         model: LoginContract.LoginModel) : LoginContract.LoginPresenter {

    lateinit var mView: LoginContract.LoginView

    private var mSessionManager: SessionManager = sessionManager
    private var mCompositeDisposable: CompositeDisposable = compositeDisposable
    private var mModel: LoginContract.LoginModel = model

    private val throwable = { t: Throwable ->
        t.printStackTrace()
        Log.d("myLogs", "Error! " + t.message)
    }

    override fun setView(view: LoginContract.LoginView) {
        mView = view
    }

    override fun subscribe() {
        mCompositeDisposable.add(mModel.getToken()
                .subscribe({ loginResponse ->
                    Log.e("myLog", "loginResponse.data.token.auth_token " + loginResponse.requestToken)
                    mSessionManager.saveAuthToken(loginResponse.requestToken)
                }, { t -> throwable }))
    }

    override fun unsubscribe() {
        mCompositeDisposable.clear()
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
        mCompositeDisposable.add(
                mModel.getSessionID(userName, password, mSessionManager.getAccessToken())
                        .subscribe({ loginResponse ->
                            mSessionManager.saveSessionID(loginResponse.sessionID)
                            Log.e("myLog", "getSessionID .sessionID " + loginResponse.sessionID)
                        }, { t -> throwable }))

    }

    private fun validatePassword(password: String) = password.length >= 4
}