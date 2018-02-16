package ua.shtain.irina.moviedbkt.view.base

import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager

/**
 * Created by Irina Shtain on 16.02.2018.
 */
abstract class BaseDialog : DialogFragment() {

    protected lateinit var mActivity: BaseActivity
    protected abstract fun initGraph()

    protected abstract fun getPresenter(): IBasePresenter<IBaseView>
    protected abstract fun getLayoutRes(): Int
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mActivity = context as BaseActivity

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(getLayoutRes(), container, false)
        initGraph()
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        getPresenter().unsubscribe()
    }

    protected fun hideKeyboard() {
        if (view != null) {
            (mActivity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(view!!.windowToken, InputMethodManager.RESULT_UNCHANGED_SHOWN)
        }
    }
}