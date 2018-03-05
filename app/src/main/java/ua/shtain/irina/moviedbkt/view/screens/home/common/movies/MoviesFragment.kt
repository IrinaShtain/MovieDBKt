package ua.shtain.irina.moviedbkt.view.screens.home.common.movies

import android.os.Bundle
import android.support.annotation.StringRes
import android.support.v7.app.AlertDialog
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearSnapHelper
import android.view.View
import com.jakewharton.rxbinding2.view.RxView
import kotlinx.android.synthetic.main.fragment_movies.*
import ua.shtain.irina.moviedbkt.R
import ua.shtain.irina.moviedbkt.other.Constants
import ua.shtain.irina.moviedbkt.view.base.refresheble_content.RefreshableFragment
import ua.shtain.irina.moviedbkt.view.screens.home.common.listeners.EndlessScrollListener
import ua.shtain.irina.moviedbkt.view.screens.home.common.listeners.OnCardClickListener
import ua.shtain.irina.moviedbkt.view.screens.home.common.listeners.OnNextPageListener
import ua.shtain.irina.moviedbkt.view.screens.home.MainActivity
import ua.shtain.irina.moviedbkt.view.screens.home.common.listeners.OnDeleteClickListener
import ua.shtain.irina.moviedbkt.view.screens.home.common.movie_details.MovieDetailsFragment
import ua.shtain.irina.moviedbkt.view.screens.home.common.movies.adapter.MovieItemAdapter
import ua.shtain.irina.moviedbkt.view.screens.home.common.movies.adapter.MovieItemDH
import ua.shtain.irina.moviedbkt.view.screens.home.common.search_movies.genre_adapter.GenreAdapter
import ua.shtain.irina.moviedbkt.view.screens.home.common.search_movies.genre_adapter.GenreDH
import ua.shtain.irina.moviedbkt.view.screens.home.common.search_movies.genre_adapter.OnGenreClickListener
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Created by Irina Shtain on 20.02.2018.
 */
abstract class MoviesFragment : RefreshableFragment(), MoviesContract.View, OnCardClickListener, OnGenreClickListener, OnDeleteClickListener {

    protected var mListID = 0
    protected var mSearchType = 0
    var mMovieAdapter: MovieItemAdapter? = null
    lateinit var mGenreAdapter: GenreAdapter
    protected lateinit var scrollListener: EndlessScrollListener

    abstract fun getSearchPresenter(): MoviesPresenter
    @StringRes
    abstract fun getToolbarTitle(): Int
    @StringRes
    abstract fun getErrorEmptyText(): Int

    override fun getLayoutRes() = R.layout.fragment_movies

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        (mActivity as MainActivity).getToolbarMan()?.setTitle(getToolbarTitle())
        setupMoviesRecyclerView()
    }

    private fun setupMoviesRecyclerView() {
        val layoutManager = GridLayoutManager(mActivity, 2)
        rvMovies.layoutManager = layoutManager
        if (mMovieAdapter == null)
            mMovieAdapter = MovieItemAdapter()
        mMovieAdapter!!.setListener(this)
        mMovieAdapter!!.setDeleteItemListener(this)
        rvMovies.adapter = mMovieAdapter
        scrollListener = EndlessScrollListener(layoutManager, object : OnNextPageListener {
            override fun onLoadMore(): Boolean {
                getSearchPresenter().getNextPage()
                return true
            }
        })
        rvMovies.addOnScrollListener(scrollListener)
    }

    override fun getSearchType() = mSearchType

    override fun setList(movieDHs: MutableList<MovieItemDH>) {
        scrollListener.reset()
        mMovieAdapter?.setListDH(movieDHs)
    }

    override fun addList(movieDHs: MutableList<MovieItemDH>) {
        mMovieAdapter?.addListDH(movieDHs)
    }

    override fun setGenres(genreItems: ArrayList<GenreDH>) {
        rlPlaceholder.visibility = View.GONE
        mGenreAdapter.setListDH(genreItems)
    }

    override fun setupSearchByTitle() {
        RxView.clicks(bt_search)
                .throttleFirst(Constants.CLICK_DELAY, TimeUnit.MILLISECONDS)
                .subscribe { _ ->
                    hideKeyboard()
                    rlPlaceholder.visibility = View.GONE
                    getSearchPresenter().onSearchClick(tvSearch.text.toString())
                }
    }

    override fun setupGenresList() {
        rvGenres.visibility = View.VISIBLE
        val layoutManager = LinearLayoutManager(mActivity, LinearLayoutManager.HORIZONTAL, false)
        rvGenres.layoutManager = layoutManager
        mGenreAdapter.setListener(this)
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(rvGenres)
        rvGenres.adapter = mGenreAdapter
    }

    override fun onCardClick(itemID: Int, position: Int) {
        mActivity.changeFragment(MovieDetailsFragment.newInstance(itemID, mListID))
        getSearchPresenter().updateNeedRefresh(false)
    }

    override fun onGenreClick(genreId: Int, position: Int) {
        getSearchPresenter().searchByGenre(genreId)
        rvGenres.smoothScrollToPosition(position)
    }

    override fun onDeleteItemClick(itemID: Int, position: Int) {
       getSearchPresenter().deleteMovie(itemID, position)
    }

    override fun showPlaceholder(placeholderType: Constants.PlaceholderType) {
        rlPlaceholder.visibility = View.VISIBLE

        when (placeholderType) {
            Constants.PlaceholderType.EMPTY -> {
                ivPlaceholderImage.setImageResource(R.drawable.placeholder_empty)
                tvPlaceholderMessage.setText(getErrorEmptyText())
            }
            Constants.PlaceholderType.NETWORK -> {
                ivPlaceholderImage.setImageResource(R.drawable.ic_cloud_off)
                tvPlaceholderMessage.setText(R.string.err_msg_connection_problem)
            }
            Constants.MessageType.UNKNOWN -> {
                ivPlaceholderImage.setImageResource(R.drawable.ic_sentiment_dissatisfied)
                tvPlaceholderMessage.setText(R.string.err_msg_something_goes_wrong)
            }
            else -> super.showPlaceholder(placeholderType)
        }
    }

    override fun showAlert(itemId: Int, position: Int) {
        val builder = AlertDialog.Builder(activity)
        builder.setMessage(R.string.question_about_deleting_movie)
        builder.setPositiveButton(R.string.answer_yes) { _, _ -> getSearchPresenter().deletionConfirmed(itemId, position) }
        builder.setNegativeButton(R.string.answer_no, null)

        builder.show()
    }

    override fun updateMovies(position: Int) {
       mMovieAdapter?.deleteItem(position)
    }
}