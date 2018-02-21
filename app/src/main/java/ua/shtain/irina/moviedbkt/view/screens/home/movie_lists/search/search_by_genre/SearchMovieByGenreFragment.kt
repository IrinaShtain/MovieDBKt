package ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.search.search_by_genre

import android.os.Bundle
import android.view.View
import ua.shtain.irina.moviedbkt.R
import ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.movies_in_list.adapter.MovieItemAdapter
import ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.search.SearchMovieFragment
import ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.search.genre_adapter.GenreAdapter
import ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.search.popular_movies.SearchPopularMovieFragment
import ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.search.popular_movies.SearchPopularMoviePresenter
import javax.inject.Inject

/**
 * Created by Irina Shtain on 21.02.2018.
 */
class SearchMovieByGenreFragment : SearchMovieFragment() {

    @Inject
    lateinit var mPresenter: SearchMovieByGenrePresenter
    @Inject
    lateinit var movieAdapter: MovieItemAdapter
    @Inject
    lateinit var genreAdapter: GenreAdapter


    companion object {
        private val LIST_ID = "list_id"
        private val SEARCH_TYPE = "search_type"
        fun newInstance(listID: Int, searchType: Int): SearchMovieByGenreFragment {
            val fragment = SearchMovieByGenreFragment()
            val bundle = Bundle()
            bundle.putInt(LIST_ID, listID)
            bundle.putInt(SEARCH_TYPE, searchType)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        mMovieAdapter = movieAdapter
        mGenreAdapter = genreAdapter
        mListID = arguments.getInt(LIST_ID)
        mSearchType = arguments.getInt(SEARCH_TYPE)
        super.onViewCreated(view, savedInstanceState)
        mPresenter.mView = this
        mPresenter.subscribe()
    }

    override fun getPresenter() = mPresenter

    override fun initGraph() {
        mActivity.mObjectGraph.getHomeComponent().inject(this)
    }

    override fun getSearchPresenter() = mPresenter

    override fun getToolbarTitle()= R.string.menu_fab_find_by_genres
}