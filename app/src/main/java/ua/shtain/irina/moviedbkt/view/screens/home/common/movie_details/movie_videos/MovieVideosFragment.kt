package ua.shtain.irina.moviedbkt.view.screens.home.common.movie_details.movie_videos

import android.os.Bundle
import android.util.Log
import android.view.View
import ua.shtain.irina.moviedbkt.view.screens.home.common.videos.VideosFragment
import javax.inject.Inject

/**
 * Created by Irina Shtain on 27.03.2018.
 */
class MovieVideosFragment : VideosFragment() {

    @Inject
    lateinit var mPresenter: MovieVideosPresenter

    override fun getVideosPresenter() = mPresenter

    companion object {
        private val MOVIE_ID = "movie_id"
        private val MOVIE_TITLE = "movie_title"
        fun newInstance(movieID: Int, title: String): MovieVideosFragment {
            val fragment = MovieVideosFragment()
            val bundle = Bundle()
            bundle.putInt(MOVIE_ID, movieID)
            bundle.putString(MOVIE_TITLE, title)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mID = arguments.getInt(MOVIE_ID)
        mTitle = arguments.getString(MOVIE_TITLE)
        Log.e("My Log", " onViewCreated")
        mPresenter.mView = this
        mPresenter.subscribe()
    }

    override fun initGraph() {
        mActivity.mObjectGraph.getHomeComponent().inject(this)
    }
}