package ua.shtain.irina.moviedbkt.view.screens.home.home_fragment

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.fragment_content_tabs.*
import ua.shtain.irina.moviedbkt.R
import ua.shtain.irina.moviedbkt.view.base.IBasePresenter
import ua.shtain.irina.moviedbkt.view.base.content.ContentView
import ua.shtain.irina.moviedbkt.view.base.tabs.ContentTabsFragment
import ua.shtain.irina.moviedbkt.view.base.tabs.TabPagerAdapter
import ua.shtain.irina.moviedbkt.view.screens.home.home_fragment.favorite_movies.FavoriteMovieFragment
import ua.shtain.irina.moviedbkt.view.screens.home.home_fragment.favorite_shows.FavoriteTvShowsFragment
import ua.shtain.irina.moviedbkt.view.screens.home.home_fragment.user_profile.UserProfileFragment
import ua.shtain.irina.moviedbkt.view.screens.home.home_fragment.watchlist_movies.WatchListMovieFragment
import ua.shtain.irina.moviedbkt.view.screens.home.home_fragment.watchlist_shows.WatchListTvShowsFragment

/**
 * Created by Irina Shtain on 22.02.2018.
 */
class HomeFragment : ContentTabsFragment() {
    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tlTabs_FCT.getTabAt(0)?.setIcon(R.drawable.ic_user_white)
        tlTabs_FCT.getTabAt(1)?.setIcon(R.drawable.ic_favorite)
        tlTabs_FCT.getTabAt(2)?.setIcon(R.drawable.ic_watch_list)
        tlTabs_FCT.getTabAt(3)?.setIcon(R.drawable.ic_favorite)
        tlTabs_FCT.getTabAt(4)?.setIcon(R.drawable.ic_watch_list)
    }

    override fun addFragmentsToAdapter(adapter: TabPagerAdapter) {
        adapter.addFragment(UserProfileFragment(), getString(R.string.tab_title_profile))
        adapter.addFragment(FavoriteMovieFragment(), getString(R.string.tab_title_favorite_movies))
        adapter.addFragment(WatchListMovieFragment(), getString(R.string.tab_title_watchlist_movies))
        adapter.addFragment(FavoriteTvShowsFragment(), getString(R.string.tab_title_favorite_shows))
        adapter.addFragment(WatchListTvShowsFragment(), getString(R.string.tab_title_watchlist_shows))
    }

    override fun getLayoutRes() = R.layout.fragment_content_tabs

    override fun getPresenter(): IBasePresenter<ContentView>? {
        return null
    }

    override fun initGraph() {

    }
}