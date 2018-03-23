package ua.shtain.irina.moviedbkt.view.screens.home.common.movie_details.videos

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.fragment_recycler_view.*
import kotlinx.android.synthetic.main.view_placeholder.*
import ua.shtain.irina.moviedbkt.R
import ua.shtain.irina.moviedbkt.other.Constants
import ua.shtain.irina.moviedbkt.view.base.refresheble_content.RefreshableFragment
import ua.shtain.irina.moviedbkt.view.base.refresheble_content.RefreshablePresenter
import ua.shtain.irina.moviedbkt.view.screens.home.MainActivity
import ua.shtain.irina.moviedbkt.view.screens.home.common.listeners.OnVideoClickListener
import ua.shtain.irina.moviedbkt.view.screens.home.common.movie_details.videos.adapter.VideoAdapter
import ua.shtain.irina.moviedbkt.view.screens.home.common.movie_details.videos.adapter.VideoItemDH
import javax.inject.Inject
import android.support.v4.content.ContextCompat.startActivity
import android.content.ActivityNotFoundException
import android.R.id
import android.content.Intent
import android.net.Uri
import android.util.Log


/**
 * Created by Irina Shtain on 22.03.2018.
 */
class VideosFragment : RefreshableFragment(), VideosContract.View, OnVideoClickListener {


    @Inject
    lateinit var mPresenter: VideosPresenter
    var mAdapter: VideoAdapter = VideoAdapter()

    private var mMovieID = 0
    private var mMovieTitle = ""

    override fun getLayoutRes() = R.layout.fragment_recycler_view

    companion object {
        private val MOVIE_ID = "movie_id"
        private val MOVIE_TITLE = "movie_title"
        fun newInstance(movieID: Int, title: String): VideosFragment {
            val fragment = VideosFragment()
            val bundle = Bundle()
            bundle.putInt(MOVIE_ID, movieID)
            bundle.putString(MOVIE_TITLE, title)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun initGraph() {
        mActivity.mObjectGraph.getHomeComponent().inject(this)
    }

    override fun getPresenter() = mPresenter as RefreshablePresenter

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mMovieID = arguments.getInt(MOVIE_ID)
        mMovieTitle = arguments.getString(MOVIE_TITLE)
        setupRecyclerView()
        Log.e("My Log", " onViewCreated")
        mPresenter.mView = this
        mPresenter.subscribe()
    }

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(context)
        rvItems!!.layoutManager = layoutManager
        mAdapter.setListener(this)
        rvItems.adapter = mAdapter
    }

    override fun onStart() {
        super.onStart()
        (mActivity as MainActivity).getToolbarMan()?.setTitle(mMovieTitle)
    }

    override fun onVideoClick(videoUrl: String) {
        mPresenter.videoItemPressed(videoUrl)
    }

    override fun setList(itemDHs: MutableList<VideoItemDH>) {
        mAdapter.setListDH(itemDHs)
    }

    override fun openVideo(url: String) {
        val appIntent = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + url))
        val webIntent = Intent(Intent.ACTION_VIEW,
                Uri.parse("http://www.youtube.com/watch?v=" + url))
        try {
            context.startActivity(appIntent)
        } catch (ex: ActivityNotFoundException) {
            context.startActivity(webIntent)
        }

    }

    override fun getMovieID() = mMovieID

    override fun showPlaceholder(placeholderType: Constants.PlaceholderType) {
        super.showPlaceholder(placeholderType)
        if (placeholderType === Constants.PlaceholderType.EMPTY) {
            ivPlaceholderImage_VC.setImageResource(R.drawable.placeholder_empty)
            tvPlaceholderMessage_VC.setText(R.string.err_msg_no_videos)
        }
    }

}