package ua.shtain.irina.moviedbkt.view.screens.home.common.search_shows.top_rated_shows

import android.os.Bundle
import android.view.View
import ua.shtain.irina.moviedbkt.R
import ua.shtain.irina.moviedbkt.other.Constants
import ua.shtain.irina.moviedbkt.view.screens.home.common.tv_shows.TvShowsFragment
import javax.inject.Inject

/**
 * Created by Irina Shtain on 02.03.2018.
 */
class SearchTopRatedTvShowsFragment : TvShowsFragment() {

    @Inject
    lateinit var mPresenter: SearchTopRatedTvShowsPresenter

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        mShowsType = Constants.SEARCH_TYPE_TVSHOWS
        super.onViewCreated(view, savedInstanceState)
        mPresenter.mView = this
        mPresenter.subscribe()
    }

    override fun getPresenter() = mPresenter

    override fun initGraph() {
        mActivity.mObjectGraph.getHomeComponent().inject(this)
    }

    override fun getSearchPresenter() = mPresenter

    override fun getToolbarTitle() = R.string.title_top_rated_shows

    override fun getErrorEmptyText() = R.string.error_msg_no_tv_shows
}
