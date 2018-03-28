package ua.shtain.irina.moviedbkt.view.screens.home.home_fragment.watchlist_shows

import android.os.Bundle
import android.view.View
import ua.shtain.irina.moviedbkt.R
import ua.shtain.irina.moviedbkt.other.Constants
import ua.shtain.irina.moviedbkt.view.screens.home.common.tv_shows.TvShowsFragment
import javax.inject.Inject

/**
 * Created by Irina Shtain on 02.03.2018.
 */
class WatchListTvShowsFragment : TvShowsFragment() {

    @Inject
    lateinit var mPresenter: WatchListTvShowsPresenter
    private var mNeedRefresh = true

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        mShowsType = Constants.TYPE_WATCHLIST_TV_SHOWS
        mPresenter.updateNeedRefresh(mNeedRefresh)
        mNeedRefresh = false
        super.onViewCreated(view, savedInstanceState)
        setupFabMenu()
        mPresenter.mView = this
        mPresenter.subscribe()
    }

    private fun setupFabMenu() {
        fabManager?.attachListID(0)
        fabManager?.showFabMenu(true, false)
    }

    override fun initGraph() {
        mActivity.mObjectGraph.getHomeComponent().inject(this)
    }

    override fun getSearchPresenter() = mPresenter

    override fun getToolbarTitle() = R.string.title_home

    override fun getErrorEmptyText() = R.string.error_msg_no_tv_shows_added_to_watchlist
}