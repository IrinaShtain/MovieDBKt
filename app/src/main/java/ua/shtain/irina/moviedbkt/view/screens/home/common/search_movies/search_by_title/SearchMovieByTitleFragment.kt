package ua.shtain.irina.moviedbkt.view.screens.home.common.search_movies.search_by_title

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.fragment_movies.*
import ua.shtain.irina.moviedbkt.R
import ua.shtain.irina.moviedbkt.other.Constants
import ua.shtain.irina.moviedbkt.view.screens.home.common.movies.MoviesFragment
import javax.inject.Inject

/**
 * Created by Irina Shtain on 21.02.2018.
 */
class SearchMovieByTitleFragment : MoviesFragment() {

    @Inject
    lateinit var mPresenter: SearchMovieByTitlePresenter

    companion object {
        private val LIST_ID = "list_id"
        fun newInstance(listID: Int): SearchMovieByTitleFragment {
            val fragment = SearchMovieByTitleFragment()
            val bundle = Bundle()
            bundle.putInt(LIST_ID, listID)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        mListID = arguments.getInt(LIST_ID)
        mSearchType = Constants.SEARCH_TYPE_MOVIES_BY_TITLE
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