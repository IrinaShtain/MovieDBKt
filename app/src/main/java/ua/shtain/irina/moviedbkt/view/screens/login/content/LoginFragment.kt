package ua.shtain.irina.moviedbkt.view.screens.login.content

import android.content.Intent
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.util.Log
import android.view.View
import com.jakewharton.rxbinding2.view.RxView
import kotlinx.android.synthetic.main.fragment_login.*
import ua.shtain.irina.moviedbkt.R
import ua.shtain.irina.moviedbkt.other.Constants
import ua.shtain.irina.moviedbkt.view.base.IBasePresenter
import ua.shtain.irina.moviedbkt.view.base.content.ContentFragment
import ua.shtain.irina.moviedbkt.view.base.content.ContentView
import ua.shtain.irina.moviedbkt.view.screens.home.MainActivity
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by Irina Shtain on 12.02.2018.
 */
class LoginFragment : ContentFragment(), LoginContract.LoginView {
    @Inject
    lateinit var mPresenter: LoginPresenter

    override fun getLayoutRes(): Int {
        return R.layout.fragment_login
    }

    override fun getPresenter(): IBasePresenter<ContentView>? {
        return mPresenter as IBasePresenter<ContentView>
    }

    override fun initGraph() {
        mActivity.mObjectGraph.getLoginComponent().inject(this)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupPresenter()
    }

    private fun setupUI() {
        btSignUp.movementMethod = LinkMovementMethod.getInstance()
        RxView.clicks(btSignIn)
                .throttleFirst(Constants.CLICK_DELAY, TimeUnit.MILLISECONDS)
                .subscribe { _ ->
                    Log.e("myLog", "bt_signIn ")
                    hideKeyboard()
                    mPresenter.onSignInClick(tilUserNameContainer.editText!!.text.toString(), tilPasswordContainer.editText!!.text.toString())
                }
    }

    private fun setupPresenter() {
        mPresenter.mView = this
        mPresenter.subscribe()
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
        val intent = Intent(context, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        mActivity.finish()
    }

}