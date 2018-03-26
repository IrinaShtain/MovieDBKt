package ua.shtain.irina.moviedbkt.view.screens.home.common.search_movies.latest_movies

import android.os.Bundle
import android.view.View
import ua.shtain.irina.moviedbkt.R
import ua.shtain.irina.moviedbkt.other.Constants
import ua.shtain.irina.moviedbkt.view.screens.home.common.movies.MoviesFragment
import ua.shtain.irina.moviedbkt.view.screens.home.common.search_movies.popular_movies.SearchPopularMoviePresenter
import javax.inject.Inject

/**
 * Created by Irina Shtain on 21.02.2018.
 */
class SearchLatestMovieFragment : MoviesFragment() {
    @Inject
    lateinit var mPresenter: SearchPopularMoviePresenter

    companion object {
        private val LIST_ID = "list_id"
        fun newInstance(listID: Int): SearchLatestMovieFragment {
            val fragment = SearchLatestMovieFragment()
            val bundle = Bundle()
            bundle.putInt(LIST_ID, listID)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        mListID = arguments.getInt(LIST_ID)
        mSearchType = Constants.SEARCH_TYPE_POPULAR_MOVIES
        super.onViewCreated(view, savedInstanceState)
        mPresenter.mView = this
        mPresenter.subscribe()
    }

    override fun initGraph() {
        mActivity.mObjectGraph.getHomeComponent().inject(this)
    }

    override fun getSearchPresenter() = mPresenter

    override fun getToolbarTitle() = R.string.title_latest_movies

    override fun getErrorEmptyText()= R.string.error_msg_no_movies_for_search
}