package ua.shtain.irina.moviedbkt.view.screens.home.common.movie_details.recommendations

import android.os.Bundle
import android.view.View
import ua.shtain.irina.moviedbkt.R
import ua.shtain.irina.moviedbkt.other.Constants
import ua.shtain.irina.moviedbkt.view.screens.home.common.movies.MoviesFragment
import javax.inject.Inject

/**
 * Created by Irina Shtain on 21.03.2018.
 */
class RecommendedMoviesFragment : MoviesFragment() {

    @Inject
    lateinit var mPresenter: RecommendedMoviesPresenter

    companion object {
        private val MOVIE_ID = "movie_id"
        fun newInstance(movieID: Int): RecommendedMoviesFragment {
            val fragment = RecommendedMoviesFragment()
            val bundle = Bundle()
            bundle.putInt(MOVIE_ID, movieID)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        mMovieID = arguments.getInt(MOVIE_ID)
        mSearchType = Constants.TYPE_RECOMMENDED_MOVIES
        super.onViewCreated(view, savedInstanceState)
        mPresenter.mView = this
        mPresenter.subscribe()
    }

    override fun getPresenter() = mPresenter

    override fun initGraph() {
        mActivity.mObjectGraph.getHomeComponent().inject(this)
    }

    override fun getSearchPresenter() = mPresenter

    override fun getToolbarTitle() = R.string.title_recommended_movies

    override fun getErrorEmptyText() = R.string.error_msg_no_recommended_movies
}