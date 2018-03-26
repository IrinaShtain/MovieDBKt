package ua.shtain.irina.moviedbkt.view.screens.home.common.search_movies.search_by_genre

import android.os.Bundle
import android.view.View
import ua.shtain.irina.moviedbkt.R
import ua.shtain.irina.moviedbkt.other.Constants
import ua.shtain.irina.moviedbkt.view.screens.home.common.movies.MoviesFragment
import ua.shtain.irina.moviedbkt.view.screens.home.common.search_movies.genre_adapter.GenreAdapter
import javax.inject.Inject

/**
 * Created by Irina Shtain on 21.02.2018.
 */
class SearchMovieByGenreFragment : MoviesFragment() {

    @Inject
    lateinit var mPresenter: SearchMovieByGenrePresenter
    @Inject
    lateinit var genreAdapter: GenreAdapter


    companion object {
        private val LIST_ID = "list_id"
        fun newInstance(listID: Int): SearchMovieByGenreFragment {
            val fragment = SearchMovieByGenreFragment()
            val bundle = Bundle()
            bundle.putInt(LIST_ID, listID)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        mGenreAdapter = genreAdapter
        mListID = arguments.getInt(LIST_ID)
        mSearchType = Constants.SEARCH_TYPE_MOVIES_BY_GENRE
        super.onViewCreated(view, savedInstanceState)
        mPresenter.mView = this
        mPresenter.subscribe()
    }

    override fun initGraph() {
        mActivity.mObjectGraph.getHomeComponent().inject(this)
    }

    override fun getSearchPresenter() = mPresenter

    override fun getToolbarTitle()= R.string.menu_fab_find_by_genres

    override fun getErrorEmptyText()= R.string.error_msg_no_movies_with_such_genre
}