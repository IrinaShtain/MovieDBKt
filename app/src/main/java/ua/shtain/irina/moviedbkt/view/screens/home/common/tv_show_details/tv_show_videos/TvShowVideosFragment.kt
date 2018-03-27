package ua.shtain.irina.moviedbkt.view.screens.home.common.tv_show_details.tv_show_videos

import android.os.Bundle
import android.util.Log
import android.view.View
import ua.shtain.irina.moviedbkt.view.screens.home.common.videos.VideosFragment
import javax.inject.Inject

/**
 * Created by Irina Shtain on 27.03.2018.
 */
class TvShowVideosFragment : VideosFragment() {

    @Inject
    lateinit var mPresenter: TvShowVideosPresenter

    override fun getVideosPresenter() = mPresenter

    companion object {
        private val TV_SHOW_ID = "tv_show_id"
        private val TV_SHOW_TITLE = "movie_title"
        fun newInstance(movieID: Int, title: String): TvShowVideosFragment {
            val fragment = TvShowVideosFragment()
            val bundle = Bundle()
            bundle.putInt(TV_SHOW_ID, movieID)
            bundle.putString(TV_SHOW_TITLE, title)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mID = arguments.getInt(TV_SHOW_ID)
        mTitle = arguments.getString(TV_SHOW_TITLE)
        Log.e("My Log", " onViewCreated")
        mPresenter.mView = this
        mPresenter.subscribe()
    }

    override fun initGraph() {
        mActivity.mObjectGraph.getHomeComponent().inject(this)
    }
}