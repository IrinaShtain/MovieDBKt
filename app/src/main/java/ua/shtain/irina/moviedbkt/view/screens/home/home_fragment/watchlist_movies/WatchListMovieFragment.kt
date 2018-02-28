package ua.shtain.irina.moviedbkt.view.screens.home.home_fragment.watchlist_movies

import android.os.Bundle
import android.view.View
import ua.shtain.irina.moviedbkt.R
import ua.shtain.irina.moviedbkt.view.screens.home.common.movies.MoviesFragment
import ua.shtain.irina.moviedbkt.view.screens.home.common.movies.adapter.MovieItemAdapter
import ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.search.genre_adapter.GenreAdapter
import ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.search.popular_movies.SearchPopularMovieFragment
import ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.search.popular_movies.SearchPopularMoviePresenter
import javax.inject.Inject

/**
 * Created by Irina Shtain on 22.02.2018.
 */
class WatchListMovieFragment : MoviesFragment() {

    @Inject
    lateinit var mPresenter: WatchListMoviePresenter


    companion object {
        private val SEARCH_TYPE = "search_type"
        fun newInstance(searchType: Int): WatchListMovieFragment {
            val fragment = WatchListMovieFragment()
            val bundle = Bundle()
            bundle.putInt(SEARCH_TYPE, searchType)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        mSearchType = arguments.getInt(SEARCH_TYPE)
        super.onViewCreated(view, savedInstanceState)
        setupFabMenu()
        mPresenter.mView = this
        mPresenter.subscribe()
    }

    private fun setupFabMenu() {
        fabManager?.attachListID(0)
        fabManager?.showFabMenu(true)
    }

    override fun getPresenter() = mPresenter

    override fun initGraph() {
        mActivity.mObjectGraph.getHomeComponent().inject(this)
    }

    override fun getSearchPresenter() = mPresenter

    override fun getToolbarTitle() = R.string.app_name
}