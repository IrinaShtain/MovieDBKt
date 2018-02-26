package ua.shtain.irina.moviedbkt.view.base.tabs

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.fragment_content_tabs.*
import ua.shtain.irina.moviedbkt.view.base.content.ContentFragment
import ua.shtain.irina.moviedbkt.view.screens.home.MainActivity

/**
 * Created by Irina Shtain on 22.02.2018.
 */
abstract class ContentTabsFragment : ContentFragment() {

    var tabPagerAdapter: TabPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tabPagerAdapter = TabPagerAdapter(childFragmentManager)
        addFragmentsToAdapter(tabPagerAdapter!!)
    }

    abstract fun addFragmentsToAdapter(adapter: TabPagerAdapter)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (mActivity as MainActivity).getToolbarMan()?.enableToolbarElevation(false)
        vpContent_FCT!!.adapter = tabPagerAdapter
        tlTabs_FCT!!.setupWithViewPager(vpContent_FCT)

    }


    override fun onDestroyView() {
        super.onDestroyView()
        (mActivity as MainActivity).getToolbarMan()?.enableToolbarElevation(true)
    }
}
