package ua.shtain.irina.moviedbkt.view.screens.home.common.videos

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
import ua.shtain.irina.moviedbkt.view.screens.home.common.videos.adapter.VideoAdapter
import ua.shtain.irina.moviedbkt.view.screens.home.common.videos.adapter.VideoItemDH
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri


/**
 * Created by Irina Shtain on 22.03.2018.
 */
abstract class VideosFragment : RefreshableFragment(), VideosContract.View, OnVideoClickListener {

    var mAdapter: VideoAdapter = VideoAdapter()

    protected var mID = 0
    protected var mTitle = ""

    override fun getLayoutRes() = R.layout.fragment_recycler_view

    abstract fun getVideosPresenter(): VideosPresenter

    override fun getPresenter() = getVideosPresenter() as RefreshablePresenter

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(context)
        rvItems!!.layoutManager = layoutManager
        mAdapter.setListener(this)
        rvItems.adapter = mAdapter
    }

    override fun onStart() {
        super.onStart()
        (mActivity as MainActivity).getToolbarMan()?.setTitle(mTitle)
    }

    override fun onVideoClick(videoUrl: String) {
        getVideosPresenter().videoItemPressed(videoUrl)
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

    override fun getMovieID() = mID

    override fun showPlaceholder(placeholderType: Constants.PlaceholderType) {
        super.showPlaceholder(placeholderType)
        if (placeholderType === Constants.PlaceholderType.EMPTY) {
            ivPlaceholderImage_VC.setImageResource(R.drawable.placeholder_empty)
            tvPlaceholderMessage_VC.setText(R.string.err_msg_no_videos)
        }
    }

}