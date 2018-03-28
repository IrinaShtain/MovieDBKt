package ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.movies_in_list

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.GridLayoutManager
import android.view.View
import android.widget.ImageView
import kotlinx.android.synthetic.main.fragment_recycler_view.*
import kotlinx.android.synthetic.main.view_placeholder.*
import ua.shtain.irina.moviedbkt.R
import ua.shtain.irina.moviedbkt.other.Constants
import ua.shtain.irina.moviedbkt.view.base.refresheble_content.RefreshableFragment
import ua.shtain.irina.moviedbkt.view.screens.home.MainActivity
import ua.shtain.irina.moviedbkt.view.screens.home.common.listeners.OnDeleteClickListener
import ua.shtain.irina.moviedbkt.view.screens.home.common.listeners.OnItemClickListener
import ua.shtain.irina.moviedbkt.view.screens.home.common.movie_details.MovieDetailsFragment
import ua.shtain.irina.moviedbkt.view.screens.home.common.movies.adapter.MovieItemAdapter
import ua.shtain.irina.moviedbkt.view.screens.home.common.movies.adapter.MovieItemDH
import javax.inject.Inject

/**
 * Created by Irina Shtain on 19.02.2018.
 */
class MoviesInListFragment : RefreshableFragment(), MoviesInListContract.View, OnItemClickListener, OnDeleteClickListener {


    @Inject
    lateinit var mPresenter: MoviesInListPresenter
    @Inject
    lateinit var movieAdapter: MovieItemAdapter

    private var mListID = 0
    private var mListTitle = ""


    override fun getLayoutRes(): Int {
        return R.layout.fragment_recycler_view
    }

    companion object {
        private val LIST_ID = "list_id"
        private val LIST_TITLE = "list_title"
        fun newInstance(listID: Int, title: String): MoviesInListFragment {
            val fragment = MoviesInListFragment()
            val bundle = Bundle()
            bundle.putInt(LIST_ID, listID)
            bundle.putString(LIST_TITLE, title)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mListID = arguments.getInt(LIST_ID)
        mListTitle = arguments.getString(LIST_TITLE)
        setupRecyclerView()
        setupFabMenu()
        mPresenter.mView = this
        mPresenter.subscribe()
    }

    override fun onStart() {
        super.onStart()
        (mActivity as MainActivity).getToolbarMan()?.setTitle(mListTitle)
    }

    private fun setupFabMenu() {
        fabManager?.attachListID(mListID)
        fabManager?.showFabMenu(true)
    }

    private fun setupRecyclerView() {
        val layoutManager = GridLayoutManager(mActivity, 2)
        rvItems.layoutManager = layoutManager
        rvItems.adapter = movieAdapter
        movieAdapter.setListener(this)
        movieAdapter.setDeleteItemListener(this)
    }

    override fun initGraph() {
        mActivity.mObjectGraph.getHomeComponent().inject(this)
    }

    override fun getListID() = mListID

    override fun getPresenter() = mPresenter

    override fun onCardClick(imageView: ImageView, itemID: Int, title: String, posterUrl: String) {
        mActivity.changeFragmentWithTransition(MovieDetailsFragment.newInstance(itemID, mListID, posterUrl, title), imageView)
        mPresenter.showedDetails()
    }

    override fun onDeleteItemClick(itemID: Int, position: Int) {
        mPresenter.deleteMovieAlert(itemID, position)
    }

    override fun setLists(itemDHS: MutableList<MovieItemDH>) {
        movieAdapter.setListDH(itemDHS)
    }

    override fun updateMovies(position: Int) {
        movieAdapter.deleteItem(position)
    }

    override fun showConfirmAlert(movieID: Int, position: Int) {
        val builder = AlertDialog.Builder(activity)
        builder.setMessage(R.string.question_about_deleting_movie)
        builder.setPositiveButton(R.string.answer_yes) { _, _ -> mPresenter.deleteMovie(movieID, position) }
        builder.setNegativeButton(R.string.answer_no, null)

        builder.show()
    }

    override fun showPlaceholder(placeholderType: Constants.PlaceholderType) {
        super.showPlaceholder(placeholderType)
        if (placeholderType === Constants.PlaceholderType.EMPTY) {
            ivPlaceholderImage_VC.setImageResource(R.drawable.placeholder_empty)
            tvPlaceholderMessage_VC.setText(R.string.error_msg_no_movies)
        }
    }

}