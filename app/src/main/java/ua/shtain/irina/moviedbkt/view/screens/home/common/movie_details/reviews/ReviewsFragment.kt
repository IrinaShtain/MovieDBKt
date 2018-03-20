package ua.shtain.irina.moviedbkt.view.screens.home.common.movie_details.reviews

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
import ua.shtain.irina.moviedbkt.view.screens.home.common.listeners.EndlessScrollListener
import ua.shtain.irina.moviedbkt.view.screens.home.common.listeners.OnNextPageListener
import ua.shtain.irina.moviedbkt.view.screens.home.common.movie_details.reviews.adapter.ReviewItemAdapter
import ua.shtain.irina.moviedbkt.view.screens.home.common.movie_details.reviews.adapter.ReviewItemDH
import javax.inject.Inject

/**
 * Created by Alex Shtain on 20.03.2018.
 */
class ReviewsFragment : RefreshableFragment(), ReviewsContract.View {

    @Inject
    lateinit var mPresenter: ReviewsPresenter
    var mAdapter: ReviewItemAdapter = ReviewItemAdapter()

    private var mMovieID = 0
    private var mMovieTitle = ""

    override fun getLayoutRes() = R.layout.fragment_recycler_view

    companion object {
        private val MOVIE_ID = "movie_id"
        private val MOVIE_TITLE = "movie_title"
        fun newInstance(movieID: Int, title: String): ReviewsFragment {
            val fragment = ReviewsFragment()
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
        mPresenter.mView = this
        mPresenter.subscribe()
    }

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(context)
        rvItems!!.layoutManager = layoutManager
        rvItems.adapter = mAdapter
        rvItems.addOnScrollListener(EndlessScrollListener(layoutManager, object : OnNextPageListener {
            override fun onLoadMore(): Boolean {
                mPresenter.getNextPage()
                return true
            }
        }))
    }

    override fun onStart() {
        super.onStart()
        (mActivity as MainActivity).getToolbarMan()?.setTitle(mMovieTitle)
    }

    override fun setList(reviewItemDHs: MutableList<ReviewItemDH>) {
        mAdapter.setListDH(reviewItemDHs)
    }

    override fun addList(reviewItemDHs: MutableList<ReviewItemDH>) {
        mAdapter.addListDH(reviewItemDHs)
    }

    override fun getMovieID() = mMovieID

    override fun showPlaceholder(placeholderType: Constants.PlaceholderType) {
        super.showPlaceholder(placeholderType)
        if (placeholderType === Constants.PlaceholderType.EMPTY) {
            ivPlaceholderImage_VC.setImageResource(R.drawable.placeholder_empty)
            tvPlaceholderMessage_VC.setText(R.string.err_msg_no_reviews)
        }
    }

}