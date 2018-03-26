package ua.shtain.irina.moviedbkt.view.screens.home.common.tv_show_details.tv_show_reviews

import android.os.Bundle
import android.view.View
import ua.shtain.irina.moviedbkt.view.screens.home.common.reviews.ReviewsFragment
import javax.inject.Inject

/**
 * Created by Irina Shtain on 26.03.2018.
 */
class TvShowReviewsFragment : ReviewsFragment() {

    @Inject
    lateinit var mPresenter: TvShowReviewsPresenter

    override fun getReviewsPresenter() = mPresenter

    companion object {
        private val TV_SHOW_ID = "tv_show_id"
        private val TV_SHOW_TITLE = "tv_show_title"
        fun newInstance(movieID: Int, title: String): TvShowReviewsFragment {
            val fragment = TvShowReviewsFragment()
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
        mPresenter.mView = this
        mPresenter.subscribe()
    }

    override fun initGraph() {
        mActivity.mObjectGraph.getHomeComponent().inject(this)
    }
}