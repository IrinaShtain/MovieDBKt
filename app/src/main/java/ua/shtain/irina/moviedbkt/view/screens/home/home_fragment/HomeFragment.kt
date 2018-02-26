package ua.shtain.irina.moviedbkt.view.screens.home.home_fragment

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.fragment_content_tabs.*
import ua.shtain.irina.moviedbkt.R
import ua.shtain.irina.moviedbkt.other.Constants
import ua.shtain.irina.moviedbkt.view.base.IBasePresenter
import ua.shtain.irina.moviedbkt.view.base.content.ContentView
import ua.shtain.irina.moviedbkt.view.base.tabs.ContentTabsFragment
import ua.shtain.irina.moviedbkt.view.base.tabs.TabPagerAdapter
import ua.shtain.irina.moviedbkt.view.screens.home.home_fragment.favorite_movies.FavoriteMovieFragment
import ua.shtain.irina.moviedbkt.view.screens.home.home_fragment.user_profile.UserProfileFragment
import ua.shtain.irina.moviedbkt.view.screens.home.home_fragment.watchlist_movies.WatchListMovieFragment

/**
 * Created by Irina Shtain on 22.02.2018.
 */
class HomeFragment : ContentTabsFragment() {
    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tlTabs_FCT.getTabAt(0)?.setIcon(R.drawable.ic_user_white)
        tlTabs_FCT.getTabAt(1)?.setIcon(R.drawable.ic_favorite)
        tlTabs_FCT.getTabAt(2)?.setIcon(R.drawable.ic_watch_list)
    }

    override fun addFragmentsToAdapter(adapter: TabPagerAdapter) {
        adapter.addFragment(UserProfileFragment(), "User Profile" )
        adapter.addFragment(FavoriteMovieFragment.newInstance(Constants.TYPE_FAVORITE_MOVIES), "Favorite Movies" )
        adapter.addFragment(WatchListMovieFragment.newInstance(Constants.TYPE_WATCHLIST_MOVIES), "Watchlist Movies" )
    }

    override fun getLayoutRes() = R.layout.fragment_content_tabs

    override fun getPresenter(): IBasePresenter<ContentView>? {
        return null
    }

    override fun initGraph() {

    }
}