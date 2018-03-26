package ua.shtain.irina.moviedbkt.view.screens.home.settings

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import com.jakewharton.rxbinding2.view.RxView
import kotlinx.android.synthetic.main.fragment_settings.*
import ua.shtain.irina.moviedbkt.R
import ua.shtain.irina.moviedbkt.other.Constants
import ua.shtain.irina.moviedbkt.view.base.IBasePresenter
import ua.shtain.irina.moviedbkt.view.base.content.ContentFragment
import ua.shtain.irina.moviedbkt.view.base.content.ContentView
import ua.shtain.irina.moviedbkt.view.screens.home.MainActivity
import ua.shtain.irina.moviedbkt.view.screens.login.LoginActivity
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by Irina Shtain on 26.03.2018.
 */
class SettingsFragment : ContentFragment(), SettingsContract.View {
    @Inject
    lateinit var mPresenter: SettingsPresenter

    override fun getLayoutRes() = R.layout.fragment_settings

    override fun getPresenter() = mPresenter as IBasePresenter<ContentView>

    override fun initGraph() {
        mActivity.mObjectGraph.getHomeComponent().inject(this)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (mActivity as MainActivity).getToolbarMan()?.setTitle(R.string.title_settings)
        initUI()
        mPresenter.mView = this
        mPresenter.subscribe()
    }


    private fun initUI() {
        RxView.clicks(tvLogout)
                .throttleFirst(Constants.CLICK_DELAY, TimeUnit.MILLISECONDS)
                .subscribe { mPresenter.logoutPressed() }
    }


    override fun showAlertAboutLogout() {
        val builder = AlertDialog.Builder(activity)
        builder.setMessage(R.string.question_about_logout)
        builder.setPositiveButton(R.string.answer_yes) { _, _ -> mPresenter.clearUser() }
        builder.setNegativeButton(R.string.answer_no, null)

        builder.show()
    }

    override fun openLogin() {
        val intent = Intent(context, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        mActivity.finish()
    }
}