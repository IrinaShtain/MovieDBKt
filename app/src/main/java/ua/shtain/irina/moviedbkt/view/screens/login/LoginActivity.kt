package ua.shtain.irina.moviedbkt.view.screens.login

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.text.method.LinkMovementMethod
import android.util.Log
import com.jakewharton.rxbinding2.view.RxView
import kotlinx.android.synthetic.main.activity_login.*
import ua.shtain.irina.moviedbkt.R
import ua.shtain.irina.moviedbkt.view.base.BaseActivity
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by Irina Shtain on 09.02.2018.
 */
class LoginActivity : BaseActivity(), LoginContract.LoginView {

    @Inject
    lateinit var mPresenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mPresenter.mView = this
        setupUI()
        Log.e("myLog", "initUI ")
    }

    private fun setupUI() {
        btSignUp.movementMethod = LinkMovementMethod.getInstance()
        RxView.clicks(btSignIn)
                .throttleFirst(600, TimeUnit.MILLISECONDS)
                .subscribe { aVoid ->
                    Log.e("myLog", "bt_signIn ")
                    hideKeyboard()
                    mPresenter.onSignInClick(tilUserNameContainer.editText!!.text.toString(), tilPasswordContainer.editText!!.text.toString())
                }
    }

    override fun getToolbar(): Toolbar? {
        return null
    }

    override fun init() {
        mObjectGraph.getLoginComponent().inject(this)
    }

    override fun getContainer(): Int {
        return 0
    }

    override fun showEmptyNameError() {
        tilUserNameContainer.error = getString(R.string.error_empty_user_name)
    }

    override fun showEmptyPasswordError() {
        tilPasswordContainer.error = getString(R.string.error_empty_password)
    }

    override fun showNotValidPasswordError() {
        tilPasswordContainer.error = getString(R.string.error_not_valid_password)
    }

    override fun setUserNameErrorEnabled(isEnabled: Boolean) {
        tilUserNameContainer.isErrorEnabled = false
    }

    override fun setPasswordErrorEnabled(isEnabled: Boolean) {
        tilPasswordContainer.isErrorEnabled = false
    }

    override fun startHomeScreen() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}