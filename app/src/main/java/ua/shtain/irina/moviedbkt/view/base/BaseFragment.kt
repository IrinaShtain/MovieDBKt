package ua.shtain.irina.moviedbkt.view.base

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.transition.TransitionInflater
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import ua.shtain.irina.moviedbkt.R

/**
 * Created by Irina Shtain on 30.01.2018.
 */
abstract class BaseFragment : Fragment() {
    protected lateinit var mActivity: BaseActivity

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mActivity = context as BaseActivity
        setHasOptionsMenu(true)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        postponeEnterTransition()
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(R.transition.default_transition)
    }

    override fun onStop() {
        super.onStop()
        hideKeyboard()
    }

    protected fun hideKeyboard() {
        if (view != null) {
            (mActivity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(view!!.windowToken, InputMethodManager.RESULT_UNCHANGED_SHOWN)
        }
    }

    protected fun disableUI(disable: Boolean) {
        if (view != null && view is ViewGroup) {
            setEnabled((view as ViewGroup), !disable)
        }
    }

    protected fun setEnabled(viewGroup: ViewGroup, enabled: Boolean) {
        val childCount = viewGroup.childCount
        for (i in 0 until childCount) {
            val view = viewGroup.getChildAt(i)
            view.isEnabled = enabled
            if (view is ViewGroup) {
                setEnabled(view, enabled)
            }
        }
    }

}