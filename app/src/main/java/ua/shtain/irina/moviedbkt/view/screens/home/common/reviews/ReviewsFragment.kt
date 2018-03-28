package ua.shtain.irina.moviedbkt.view.screens.home.common.reviews

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
import ua.shtain.irina.moviedbkt.view.screens.home.common.reviews.adapter.ReviewItemAdapter
import ua.shtain.irina.moviedbkt.view.screens.home.common.reviews.adapter.ReviewItemDH

/**
 * Created by Irina Shtain on 20.03.2018.
 */
abstract class ReviewsFragment : RefreshableFragment(), ReviewsContract.View {

    var mAdapter: ReviewItemAdapter = ReviewItemAdapter()

    protected var mID = 0
    protected var mTitle = ""

    override fun getLayoutRes() = R.layout.fragment_recycler_view

    override fun getPresenter() = getReviewsPresenter() as RefreshablePresenter

    abstract fun getReviewsPresenter(): ReviewsPresenter

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(context)
        rvItems!!.layoutManager = layoutManager
        rvItems.adapter = mAdapter
        rvItems.addOnScrollListener(EndlessScrollListener(layoutManager, object : OnNextPageListener {
            override fun onLoadMore(): Boolean {
                getReviewsPresenter().getNextPage()
                return true
            }
        }))
    }

    override fun onStart() {
        super.onStart()
        (mActivity as MainActivity).getToolbarMan()?.setTitle(mTitle)
    }

    override fun setList(reviewItemDHs: MutableList<ReviewItemDH>) {
        mAdapter.setListDH(reviewItemDHs)
    }

    override fun addList(reviewItemDHs: MutableList<ReviewItemDH>) {
        mAdapter.addListDH(reviewItemDHs)
    }

    override fun getID() = mID

    override fun showPlaceholder(placeholderType: Constants.PlaceholderType) {
        super.showPlaceholder(placeholderType)
        if (placeholderType === Constants.PlaceholderType.EMPTY) {
            ivPlaceholderImage_VC.setImageResource(R.drawable.placeholder_empty)
            tvPlaceholderMessage_VC.setText(R.string.err_msg_no_reviews)
        }
    }

}