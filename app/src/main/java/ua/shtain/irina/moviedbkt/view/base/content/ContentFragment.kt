package ua.shtain.irina.moviedbkt.view.base.content

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.jakewharton.rxbinding2.view.RxView
import ua.shtain.irina.moviedbkt.R
import ua.shtain.irina.moviedbkt.other.Constants
import ua.shtain.irina.moviedbkt.view.base.IBasePresenter
import kotlinx.android.synthetic.main.view_content.*
import kotlinx.android.synthetic.main.view_placeholder.*
import ua.shtain.irina.moviedbkt.view.base.BaseFragment
import java.util.concurrent.TimeUnit

/**
 * Created by Irina Shtain on 30.01.2018.
 */
abstract class ContentFragment : BaseFragment(), ContentView {
    private lateinit var snackbar: Snackbar
    protected abstract fun getLayoutRes(): Int

    protected abstract fun getPresenter(): IBasePresenter<ContentView>?

    protected abstract fun initGraph()

    @LayoutRes
    protected fun getRootLayoutRes(): Int {
        return R.layout.view_content
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val parent = inflater.inflate(getRootLayoutRes(), container, false)
        val flContent = parent.findViewById(R.id.flContent_VC) as ViewGroup
        View.inflate(activity, getLayoutRes(), flContent)
        initGraph()
        setHasOptionsMenu(true)
        return parent
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSnackBar()
        initBtn()
    }

    protected fun initSnackBar() {
        snackbar = Snackbar.make(flContent_VC.getChildAt(0), "", Snackbar.LENGTH_SHORT)
        val textView = snackbar.view.findViewById<View>(android.support.design.R.id.snackbar_text) as TextView
        textView.maxLines = 5  // show multiple line
    }

    protected fun initBtn() {
        RxView.clicks(btnPlaceholderAction_VC)
                .throttleFirst(Constants.CLICK_DELAY, TimeUnit.MILLISECONDS)
                .subscribe { _ -> onPlaceholderAction() }
    }

    protected fun onPlaceholderAction() {
        getPresenter()?.subscribe()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.e("myLog", ("onDestroyView " + getPresenter() == null).toString())
        getPresenter()?.unsubscribe()
    }


    override fun showProgressMain() {
        hideKeyboard()
        dismissUI()
        pbMain_VC.visibility = View.VISIBLE
    }

    override fun showProgressPagination() {
        pbPagination_VC.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        pbMain_VC.visibility = View.GONE
        pbPagination_VC.visibility = View.GONE
        flContent_VC.visibility = View.VISIBLE
        rlPlaceholder_VC.visibility = View.GONE
    }

    override fun showMessage(messageType: Constants.MessageType) {
        showCustomMessage(getString(messageType.messageRes), messageType.isDangerous)
    }

    override fun showCustomMessage(msg: String, isDangerous: Boolean) {
        hideProgress()
        if (snackbar.isShown) snackbar.dismiss()
        snackbar.view.setBackgroundColor(ContextCompat.getColor(activity, if (isDangerous)
            R.color.colorAccent
        else
            R.color.colorPrimary))
        snackbar.setText(msg)
        snackbar.show()
    }

    override fun showPlaceholder(placeholderType: Constants.PlaceholderType) {
        dismissUI()
        rlPlaceholder_VC.visibility = View.VISIBLE
        ivPlaceholderImage_VC.setImageResource(placeholderType.iconRes)
        tvPlaceholderMessage_VC.setText(placeholderType.messageRes)
        btnPlaceholderAction_VC.visibility = if (placeholderType === Constants.PlaceholderType.EMPTY)
            View.GONE
        else
            View.VISIBLE
    }


    private fun dismissUI() {
        pbMain_VC.visibility = View.GONE
        pbPagination_VC.visibility = View.GONE
        flContent_VC.visibility = View.GONE
        rlPlaceholder_VC.visibility = View.GONE
    }
}