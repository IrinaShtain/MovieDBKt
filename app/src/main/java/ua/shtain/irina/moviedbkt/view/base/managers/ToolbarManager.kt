package ua.shtain.irina.moviedbkt.view.base.managers

import android.support.annotation.StringRes
import android.support.v7.app.ActionBar
import android.support.v7.widget.Toolbar
import android.util.TypedValue
import android.view.View
import ua.shtain.irina.moviedbkt.view.base.BaseActivity

/**
 * Created by Irina Shtain on 20.02.2018.
 */
class ToolbarManager constructor(toolbar: Toolbar?,
                                         activity: BaseActivity) {

    private var actionBar: ActionBar? = null
    private var mToolbar = toolbar
    private var mActivity = activity
    private var mNavigationClickListener: View.OnClickListener
    private var pxToolbarElevation: Float = 0.toFloat()

    init {
        mToolbar = toolbar
        mActivity.setSupportActionBar(toolbar)
        actionBar = mActivity.supportActionBar

        mNavigationClickListener = View.OnClickListener { mActivity.onBackPressed() }

        if (actionBar != null) {
            actionBar!!.setDisplayShowTitleEnabled(true)
            actionBar!!.setHomeButtonEnabled(true)
        }

        val r = activity.resources
        pxToolbarElevation = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4f, r.displayMetrics)
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

    fun enableToolbarElevation(isEnabled: Boolean) {
        actionBar?.elevation = if (isEnabled) pxToolbarElevation else 0.toFloat()
    }

}
