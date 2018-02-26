package ua.shtain.irina.moviedbkt.view.base.tabs

import android.graphics.drawable.Icon
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.PagerAdapter
import ua.shtain.irina.moviedbkt.view.base.content.ContentFragment
import java.util.ArrayList

/**
 * Created by Irina Shtain on 22.02.2018.
 */
class TabPagerAdapter constructor(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private var titles: ArrayList<String> = ArrayList()
    private var fragments: ArrayList<ContentFragment> = ArrayList()

    fun addFragment(fragment: ContentFragment, title: String) {
        fragments.add(fragment)
        titles.add(title)
        notifyDataSetChanged()
    }

    override fun getItem(position: Int): ContentFragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return titles[position]
    }

    override fun getItemPosition(any: Any?): Int {
        return PagerAdapter.POSITION_NONE
    }
}
