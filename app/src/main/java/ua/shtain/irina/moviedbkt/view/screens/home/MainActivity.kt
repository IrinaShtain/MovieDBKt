package ua.shtain.irina.moviedbkt.view.screens.home

import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import ua.shtain.irina.moviedbkt.R
import ua.shtain.irina.moviedbkt.view.base.BaseActivity
import ua.shtain.irina.moviedbkt.view.base.managers.ToolbarManager
import ua.shtain.irina.moviedbkt.view.screens.home.home_fragment.HomeFragment
import ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.MovieListsFragment
import ua.shtain.irina.moviedbkt.view.screens.home.settings.SettingsFragment
import ua.shtain.irina.moviedbkt.view.screens.home.stars.SearchStarFragment


class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var drawerToggle: ActionBarDrawerToggle? = null
    private var mToolBarNavigationListenerIsRegistered = false
    private var toolbarManager: ToolbarManager? = null
    override fun getToolbar(): Toolbar {
        return toolbar_MA
    }

    override fun initGraph() {
        mObjectGraph.getHomeComponent().inject(this)
    }

    override fun getContainer(): Int {
        return R.id.content_MA
    }

    fun getToolbarMan() = toolbarManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        changeFragment(HomeFragment(), true)
        toolbarManager = ToolbarManager(getToolbar(), this)
        initDrawer()
        setupToolbar()
    }

    private fun setupToolbar() {
        toolbar_MA.setTitleTextColor(Color.WHITE)
        supportFragmentManager.addOnBackStackChangedListener { enableViews(supportFragmentManager.backStackEntryCount > 1) }
    }

    private fun enableViews(enable: Boolean) {
        // To keep states of ActionBar and ActionBarDrawerToggle synchronized,
        // when you enable on one, you disable on the other.
        // And as you may notice, the order for this operation is disable first, then enable - VERY VERY IMPORTANT.
        if (enable) {
            // Remove hamburger
            drawerToggle?.isDrawerIndicatorEnabled = false
            // Show updateStatus button
            toolbarManager?.showHomeAsUp(true)
            // when DrawerToggle is disabled i.e. setDrawerIndicatorEnabled(false), navigation icon
            // clicks are disabled i.e. the UP button will not work.
            // We need to add a listener, as in below, so DrawerToggle will forward
            // click events to this listener.
            if (!mToolBarNavigationListenerIsRegistered) {
                mToolBarNavigationListenerIsRegistered = true
            }

        } else {
            // Remove updateStatus button
            toolbarManager?.showHomeAsUp(false)
            // Show hamburger
            drawerToggle?.isDrawerIndicatorEnabled = true
            // Remove the/any drawer toggle listener
            mToolBarNavigationListenerIsRegistered = false
        }

        drawerToggle?.toolbarNavigationClickListener = toolbarManager?.getNavigationClickListener(mToolBarNavigationListenerIsRegistered)
    }


    private fun initDrawer() {
        drawerToggle = ActionBarDrawerToggle(this, drawerLayout_MA, toolbar_MA, R.string.drawer_open, R.string.drawer_close)
        drawerLayout_MA.addDrawerListener(drawerToggle!!)
        drawerToggle!!.syncState()
        nvDrawer_MA.setNavigationItemSelectedListener(this)
    }

    fun updateNavigationItem(id: Int, isChecked: Boolean) {
        nvDrawer_MA.menu.findItem(id)?.isChecked = isChecked
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        drawerLayout_MA.closeDrawers()

        if (item.isChecked) {
            return false
        }

        when (item.itemId) {
            R.id.menuMyProfile -> changeFragment(HomeFragment(), true)
            R.id.menuLists -> changeFragment(MovieListsFragment(), true)
            R.id.menuReadAboutStar -> changeFragment(SearchStarFragment(), true)
            R.id.menuSettings -> changeFragment(SettingsFragment(), true)
        }
        return true
    }

    override fun onBackPressed() {
        if (drawerLayout_MA.isDrawerOpen(GravityCompat.START)) {
            drawerLayout_MA.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

}
