package ua.shtain.irina.moviedbkt.view.screens.home.user_profile

import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import com.jakewharton.rxbinding2.view.RxMenuItem
import com.squareup.picasso.Picasso
import ua.shtain.irina.moviedbkt.R
import ua.shtain.irina.moviedbkt.view.base.IBasePresenter
import ua.shtain.irina.moviedbkt.view.base.content.ContentFragment
import ua.shtain.irina.moviedbkt.view.base.content.ContentView
import javax.inject.Inject
import kotlinx.android.synthetic.main.fragment_profile.*
import ua.shtain.irina.moviedbkt.other.Constants
import ua.shtain.irina.moviedbkt.view.screens.login.LoginActivity
import java.util.concurrent.TimeUnit

/**
 * Created by Irina Shtain on 13.02.2018.
 */
class UserProfileFragment : ContentFragment(), UserProfileContract.View {

    @Inject
    lateinit var mPresenter: UserProfilePresenter

    override fun getLayoutRes() = R.layout.fragment_profile

    override fun getPresenter() = mPresenter as IBasePresenter<ContentView>

    override fun initGraph() {
        mActivity.mObjectGraph.getHomeComponent().inject(this)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPresenter.mView = this
        mPresenter.subscribe()
    }

    override fun setUserNick(name: String) {
        tvName.text = getString(R.string.name, name)
    }

    override fun setUserName(name: String) {
        if (!name.isEmpty())
            tvUserName.text = getString(R.string.username, name)
        else
            tvUserName.visibility = View.GONE
    }

    override fun setAdultPermission(hasPermission: Boolean) {
        val perm = if (hasPermission) "Yes" else "No"
        tvIncludeAdult.text = getString(R.string.include_adult, perm)
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

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        RxMenuItem.clicks(menu.findItem(R.id.logout))
                .throttleFirst(Constants.CLICK_DELAY, TimeUnit.MILLISECONDS)
                .subscribe { mPresenter.menuPressed() }
    }


    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_logout, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
}