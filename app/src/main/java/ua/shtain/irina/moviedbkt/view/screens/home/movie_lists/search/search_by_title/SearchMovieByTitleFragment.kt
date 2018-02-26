package ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.search.search_by_title

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.fragment_search.*
import ua.shtain.irina.moviedbkt.R
import ua.shtain.irina.moviedbkt.view.screens.home.common.movies.adapter.MovieItemAdapter
import ua.shtain.irina.moviedbkt.view.screens.home.common.movies.MoviesFragment
import ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.search.genre_adapter.GenreAdapter
import javax.inject.Inject

/**
 * Created by Irina Shtain on 21.02.2018.
 */
class SearchMovieByTitleFragment : MoviesFragment() {

    @Inject
    lateinit var mPresenter: SearchMovieByTitlePresenter

    companion object {
        private val LIST_ID = "list_id"
        private val SEARCH_TYPE = "search_type"
        fun newInstance(listID: Int, searchType: Int): SearchMovieByTitleFragment {
            val fragment = SearchMovieByTitleFragment()
            val bundle = Bundle()
            bundle.putInt(LIST_ID, listID)
            bundle.putInt(SEARCH_TYPE, searchType)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        mListID = arguments.getInt(LIST_ID)
        mSearchType = arguments.getInt(SEARCH_TYPE)
        llFindByTitle.visibility = View.VISIBLE
        super.onViewCreated(view, savedInstanceState)
        mPresenter.mView = this
        mPresenter.subscribe()
    }

    override fun getPresenter() = mPresenter

    override fun initGraph() {
        mActivity.mObjectGraph.getHomeComponent().inject(this)
    }

    override fun getSearchPresenter() = mPresenter

    override fun getToolbarTitle()= R.string.menu_fab_find_by_title
}