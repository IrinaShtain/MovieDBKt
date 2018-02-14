package ua.shtain.irina.moviedbkt.view.screens.home

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import ua.shtain.irina.moviedbkt.R
import ua.shtain.irina.moviedbkt.view.base.BaseActivity
import ua.shtain.irina.moviedbkt.view.screens.home.user_profile.UserProfileFragment
import android.view.MenuInflater




class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var drawerToggle: ActionBarDrawerToggle? = null
    override fun getToolbar(): Toolbar? {
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
        initUI()
        changeFragment(UserProfileFragment(), true)
    }

    private fun initUI() {
        drawerToggle = ActionBarDrawerToggle(this, drawerLayout_MA, toolbar_MA, R.string.drawer_open, R.string.drawer_close)
        drawerLayout_MA.addDrawerListener(drawerToggle!!)
        drawerToggle!!.syncState()
        nvDrawer_MA.setNavigationItemSelectedListener(this)
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        drawerLayout_MA.closeDrawers()

        if (item.isChecked) {
            return false
        }

        when (item.itemId) {
            R.id.menuMyProfile -> changeFragment(UserProfileFragment(), true)
            R.id.menuLists -> Toast.makeText(this, "Menu menuLists", Toast.LENGTH_SHORT).show()
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
