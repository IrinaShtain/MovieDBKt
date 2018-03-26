package ua.shtain.irina.moviedbkt.view.screens.home.home_fragment.user_profile

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.fragment_profile.*
import ua.shtain.irina.moviedbkt.R
import ua.shtain.irina.moviedbkt.view.base.IBasePresenter
import ua.shtain.irina.moviedbkt.view.base.content.ContentFragment
import ua.shtain.irina.moviedbkt.view.base.content.ContentView
import ua.shtain.irina.moviedbkt.view.screens.home.MainActivity
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
        (mActivity as MainActivity).getToolbarMan()?.setTitle(R.string.title_home)
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
        val perm = if (hasPermission) getString(R.string.answer_yes) else getString(R.string.answer_no)
        tvIncludeAdult.text = getString(R.string.include_adult, perm)
    }
}