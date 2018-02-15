package ua.shtain.irina.moviedbkt.view.screens.home

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import ua.shtain.irina.moviedbkt.R
import ua.shtain.irina.moviedbkt.view.base.BaseActivity
import ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.MovieListsFragment
import ua.shtain.irina.moviedbkt.view.screens.home.user_profile.UserProfileFragment


class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var drawerToggle: ActionBarDrawerToggle? = null
    override fun getToolbar(): Toolbar {
        return toolbar_MA
    }

    override fun initGraph() {
        mObjectGraph.getHomeComponent().inject(this)
    }

    override fun getContainer(): Int {
        return R.id.content_MA
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar_MA?.title = getString(R.string.app_name)
        changeFragment(UserProfileFragment(), true)
    }

    override fun onStart() {
        super.onStart()
        initDrawer()
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
            R.id.menuMyProfile -> changeFragment(UserProfileFragment(), true)
            R.id.menuLists -> changeFragment(MovieListsFragment(), true)
            R.id.menuLatestMovies -> Toast.makeText(this, "Menu menuLatestMovies", Toast.LENGTH_SHORT).show()
            R.id.menuReadAboutStar -> Toast.makeText(this, "Menu menuReadAboutStar", Toast.LENGTH_SHORT).show()

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
