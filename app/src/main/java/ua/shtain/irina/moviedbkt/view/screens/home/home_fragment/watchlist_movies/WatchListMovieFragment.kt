package ua.shtain.irina.moviedbkt.view.screens.home.home_fragment.watchlist_movies

import android.os.Bundle
import android.view.View
import ua.shtain.irina.moviedbkt.R
import ua.shtain.irina.moviedbkt.other.Constants
import ua.shtain.irina.moviedbkt.view.screens.home.common.movies.MoviesFragment
import javax.inject.Inject

/**
 * Created by Irina Shtain on 22.02.2018.
 */
class WatchListMovieFragment : MoviesFragment() {

    @Inject
    lateinit var mPresenter: WatchListMoviePresenter

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        mSearchType = Constants.TYPE_WATCHLIST_MOVIES
        super.onViewCreated(view, savedInstanceState)
        setupFabMenu()
        mPresenter.mView = this
        mPresenter.subscribe()
    }

    private fun setupFabMenu() {
        fabManager?.attachListID(0)
        fabManager?.showFabMenu(true)
    }

    override fun getPresenter() = mPresenter

    override fun initGraph() {
        mActivity.mObjectGraph.getHomeComponent().inject(this)
    }

    override fun getSearchPresenter() = mPresenter

    override fun getToolbarTitle() = R.string.app_name

    override fun getErrorEmptyText()= R.string.error_msg_no_movies_added_to_watchlist
}