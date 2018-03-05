package ua.shtain.irina.moviedbkt.view.screens.home.home_fragment.user_profile

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.fragment_profile.*
import ua.shtain.irina.moviedbkt.R
import ua.shtain.irina.moviedbkt.view.base.IBasePresenter
import ua.shtain.irina.moviedbkt.view.base.content.ContentFragment
import ua.shtain.irina.moviedbkt.view.base.content.ContentView
import ua.shtain.irina.moviedbkt.view.screens.home.MainActivity
import ua.shtain.irina.moviedbkt.view.screens.login.LoginActivity
import javax.inject.Inject


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

    override fun onStart() {
        super.onStart()
        (mActivity as MainActivity).updateNavigationItem(R.id.menuMyProfile, true)
        (mActivity as MainActivity).getToolbarMan()?.setTitle(R.string.title_home)
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
        val perm = if (hasPermission) getString(R.string.answer_yes) else getString(R.string.answer_no)
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.logout -> {
                mPresenter.menuPressed()
                true
            }
            else
            -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_logout, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
}