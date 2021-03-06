package ua.shtain.irina.moviedbkt.view.screens.home.common.search_shows.on_air_shows

import android.os.Bundle
import android.view.View
import ua.shtain.irina.moviedbkt.R
import ua.shtain.irina.moviedbkt.other.Constants
import ua.shtain.irina.moviedbkt.view.screens.home.common.tv_shows.TvShowsFragment
import javax.inject.Inject

/**
 * Created by Irina Shtain on 02.03.2018.
 */
class SearchOnAirTvShowsFragment : TvShowsFragment() {

    @Inject
    lateinit var mPresenter: SearchOnAirTvShowsPresenter

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        mShowsType = Constants.SEARCH_TYPE_TVSHOWS
        super.onViewCreated(view, savedInstanceState)
        mPresenter.mView = this
        mPresenter.subscribe()
    }

    override fun initGraph() {
        mActivity.mObjectGraph.getHomeComponent().inject(this)
    }

    override fun getSearchPresenter() = mPresenter

    override fun getToolbarTitle() = R.string.title_on_air_shows

    override fun getErrorEmptyText() = R.string.error_msg_no_tv_shows
}
