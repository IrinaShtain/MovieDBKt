package ua.shtain.irina.moviedbkt.view.screens.home.common.movie_details.movie_reviews

import android.os.Bundle
import android.view.View
import ua.shtain.irina.moviedbkt.view.screens.home.common.reviews.ReviewsFragment
import javax.inject.Inject

/**
 * Created by Irina Shtain on 26.03.2018.
 */
class MovieReviewsFragment : ReviewsFragment() {

    @Inject
    lateinit var mPresenter: MovieReviewsPresenter

    override fun getReviewsPresenter() = mPresenter

    companion object {
        private val MOVIE_ID = "movie_id"
        private val MOVIE_TITLE = "movie_title"
        fun newInstance(movieID: Int, title: String): MovieReviewsFragment {
            val fragment = MovieReviewsFragment()
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
        mPresenter.mView = this
        mPresenter.subscribe()

    }

    override fun initGraph() {
        mActivity.mObjectGraph.getHomeComponent().inject(this)
    }
}