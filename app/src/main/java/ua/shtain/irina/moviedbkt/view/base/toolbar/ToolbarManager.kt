package ua.shtain.irina.moviedbkt.view.base.toolbar

import android.support.annotation.StringRes
import android.support.v7.app.ActionBar
import android.support.v7.widget.Toolbar
import android.view.View
import ua.shtain.irina.moviedbkt.view.base.BaseActivity
import javax.inject.Inject

/**
 * Created by Irina Shtain on 20.02.2018.
 */
class ToolbarManager @Inject constructor(toolbar: Toolbar?,
                                         activity: BaseActivity) {

    private var actionBar: ActionBar? = null
    private var mToolbar = toolbar
    private var mActivity = activity
    private var mNavigationClickListener: View.OnClickListener

    init {
        mToolbar = toolbar
        mActivity.setSupportActionBar(toolbar)
        actionBar = mActivity.supportActionBar

        mNavigationClickListener = View.OnClickListener { mActivity.onBackPressed() }

        if (actionBar != null) {
            actionBar!!.setDisplayShowTitleEnabled(true)
            actionBar!!.setHomeButtonEnabled(true)
        }
    }

    fun showHomeButton(show: Boolean) {
        if (actionBar != null) {
            actionBar!!.setDisplayShowHomeEnabled(show)
        }
    }

    fun showHomeAsUp(isShow: Boolean) {
        if (actionBar != null) {
            actionBar!!.setDisplayHomeAsUpEnabled(isShow)
            actionBar!!.setHomeButtonEnabled(true)
        }
    }

    fun getNavigationClickListener(need: Boolean): View.OnClickListener? {
        return if (need) mNavigationClickListener else null
    }

    fun setTitle(@StringRes title: Int) {
        if (actionBar != null) {
            actionBar!!.setTitle(title)
        }
    }

    fun setTitle(title: CharSequence) {
        if (actionBar != null) {
            actionBar!!.title = title
        }
    }

    fun displayToolbar(isShown: Boolean) {
        mToolbar?.visibility = if (isShown) View.VISIBLE else View.GONE
    }

}
