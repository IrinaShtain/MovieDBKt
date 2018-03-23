package ua.shtain.irina.moviedbkt.view.screens.home.common.tv_show_details.recommendations

import android.os.Bundle
import android.view.View
import ua.shtain.irina.moviedbkt.R
import ua.shtain.irina.moviedbkt.other.Constants
import ua.shtain.irina.moviedbkt.view.screens.home.common.tv_shows.TvShowsFragment
import javax.inject.Inject

/**
 * Created by Irina Shtain on 23.03.2018.
 */
class RecommendedTvShowsFragment : TvShowsFragment() {

    @Inject
    lateinit var mPresenter: RecommendedTvShowsPresenter
    private var needRefresh= true

    companion object {
        private val TVSHOW_ID = "tvshow_id"
        fun newInstance(tvShowID: Int): RecommendedTvShowsFragment {
            val fragment = RecommendedTvShowsFragment()
            val bundle = Bundle()
            bundle.putInt(TVSHOW_ID, tvShowID)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        mShowsType = Constants.TYPE_RECOMMENDED_SHOWS
        mShowsID = arguments.getInt(TVSHOW_ID)
        super.onViewCreated(view, savedInstanceState)
        mPresenter.mView = this
        mPresenter.updateNeedRefresh(needRefresh)
        needRefresh = false
        mPresenter.subscribe()
    }

    override fun getPresenter() = mPresenter

    override fun initGraph() {
        mActivity.mObjectGraph.getHomeComponent().inject(this)
    }

    override fun getSearchPresenter() = mPresenter

    override fun getToolbarTitle() = R.string.title_recommended_tv_shows

    override fun getErrorEmptyText() = R.string.error_msg_no_recommended_tv_shows
}
