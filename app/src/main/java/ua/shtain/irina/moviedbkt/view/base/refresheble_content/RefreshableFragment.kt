package ua.shtain.irina.moviedbkt.view.base.refresheble_content

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.content.ContextCompat
import android.view.View
import kotlinx.android.synthetic.main.view_content_refreshable.*
import ua.shtain.irina.moviedbkt.R
import ua.shtain.irina.moviedbkt.view.base.content.ContentFragment
import ua.shtain.irina.moviedbkt.view.base.toolbar.FABManager

/**
 * Created by Irina Shtain on 15.02.2018.
 */
abstract class RefreshableFragment : ContentFragment() {


    abstract override fun getPresenter(): RefreshablePresenter

    protected var fabManager: FABManager? = null

    @LayoutRes
    override fun getRootLayoutRes() = R.layout.view_content_refreshable

    fun isRefreshing() = swipeContainer_VC!!.isRefreshing

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFabManager()
        initRefreshing()
    }

    private fun initFabManager() {
        fabManager = FABManager(mActivity)
        fabManager!!.attachFabAdd(fabAdd_VC)
        fabManager!!.attachContainerFindLatest(llFindLatest)
        fabManager!!.attachContainerFindPopular(llFindPopular)
        fabManager!!.attachContainerFindUsingGenre(llFindUsingGenre)
        fabManager!!.attachContainerFindUsingTitle(llFindUsingTitle)
    }

    private fun initRefreshing() {
        swipeContainer_VC!!.isEnabled = false
        swipeContainer_VC!!.setColorSchemeColors(ContextCompat.getColor(mActivity, R.color.colorPrimaryDark))
        swipeContainer_VC!!.isEnabled = true
        swipeContainer_VC!!.setOnRefreshListener {
            closeFabMenu()
            getPresenter().onRefresh()
        }
    }

    fun closeFabMenu(){
        if (fabManager?.isFabGroupOpen()!!) fabManager?.closeFabMenu()
    }

    fun enableRefreshing(isEnabled: Boolean) {
        swipeContainer_VC!!.isEnabled = isEnabled
    }

    override fun showProgressPagination() {
        super.showProgressPagination()
        enableRefreshing(false)
    }

    override fun showProgressMain() {
        super.showProgressMain()
        enableRefreshing(false)
    }

    override fun hideProgress() {
        super.hideProgress()
        enableRefreshing(true)
        if (swipeContainer_VC!!.isRefreshing) swipeContainer_VC!!.isRefreshing = false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fabAdd_VC!!.visibility = View.GONE
    }
}
