package ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.movies_in_list

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.GridLayoutManager
import android.view.Menu
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.jakewharton.rxbinding2.view.RxView
import kotlinx.android.synthetic.main.fragment_recycler_view.*
import kotlinx.android.synthetic.main.view_content_refreshable.*
import kotlinx.android.synthetic.main.view_placeholder.*
import ua.shtain.irina.moviedbkt.R
import ua.shtain.irina.moviedbkt.model.movie.MovieItem
import ua.shtain.irina.moviedbkt.other.Constants
import ua.shtain.irina.moviedbkt.view.base.refresheble_content.RefreshableFragment
import ua.shtain.irina.moviedbkt.view.base.refresheble_content.RefreshablePresenter
import ua.shtain.irina.moviedbkt.view.screens.common.OnCardClickListener
import ua.shtain.irina.moviedbkt.view.screens.home.MainActivity
import ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.movie_details.MovieDetailsFragment
import ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.movies_in_list.adapter.MovieItemAdapter
import ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.movies_in_list.adapter.MovieItemDH
import ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.search.latest_movies.SearchLatestMovieFragment
import ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.search.popular_movies.SearchPopularMovieFragment
import ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.search.search_by_genre.SearchMovieByGenreFragment
import ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.search.search_by_title.SearchMovieByTitleFragment
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by Irina Shtain on 19.02.2018.
 */
class MoviesInListFragment : RefreshableFragment(), MoviesInListContract.View, OnCardClickListener {


    @Inject
    lateinit var mPresenter: MoviesInListPresenter
    @Inject
    lateinit var movieAdapter: MovieItemAdapter

    private var mListID = 0
    private var mListTitle = ""
    private var mAnimFabClose: Animation? = null
    private var mAnimFabOpen: Animation? = null


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
        setupAnimations()
        setupRecyclerView()
        setupFABs()
        mPresenter.mView = this
        mPresenter.subscribe()
    }

    override fun onStart() {
        super.onStart()
        (mActivity as MainActivity).getToolbarMan()?.setTitle(mListTitle)
    }

    private fun setupAnimations() {
        mAnimFabOpen = AnimationUtils.loadAnimation(activity, R.anim.menu_fab_open)
        mAnimFabClose = AnimationUtils.loadAnimation(activity, R.anim.menu_fab_close)
    }

    private fun setupFABs() {
        fabAdd_VC.visibility = View.VISIBLE
        RxView.clicks(fabAdd_VC)
                .throttleFirst(Constants.CLICK_DELAY, TimeUnit.MILLISECONDS)
                .subscribe { mPresenter.onMainFABClick() }
        RxView.clicks(fabFindUsingTitle)
                .throttleFirst(Constants.CLICK_DELAY, TimeUnit.MILLISECONDS)
                .subscribe { mPresenter.onFabFindUsingTitleClick() }
        RxView.clicks(fabFindUsingGenre)
                .throttleFirst(Constants.CLICK_DELAY, TimeUnit.MILLISECONDS)
                .subscribe { mPresenter.onFabFindUsingGenreClick() }
        RxView.clicks(fabFindPopular)
                .throttleFirst(Constants.CLICK_DELAY, TimeUnit.MILLISECONDS)
                .subscribe { mPresenter.onFabFindPopularClick() }
        RxView.clicks(fabFindLatest)
                .throttleFirst(Constants.CLICK_DELAY, TimeUnit.MILLISECONDS)
                .subscribe { mPresenter.onFabFindLatestClick() }
    }

    private fun setupRecyclerView() {
        val layoutManager = GridLayoutManager(mActivity, 2)
        rvLists.layoutManager = layoutManager
        rvLists.adapter = movieAdapter
        movieAdapter.setListener(this)
    }

    override fun initGraph() {
        mActivity.mObjectGraph.getHomeComponent().inject(this)
    }

    override fun getListID() = mListID

    override fun getPresenter(): RefreshablePresenter = mPresenter

    override fun onCardClick(itemID: Int, position: Int) {
        mPresenter.showDetails(itemID)
    }

    override fun setLists(itemDHS: ArrayList<MovieItemDH>) {
        movieAdapter.setListDH(itemDHS)
    }

    override fun openMovieDetails(movieID: Int) {
        mActivity.changeFragment(MovieDetailsFragment.newInstance(movieID, mListID))
    }

    override fun openSearchByTitleScreen(listID: Int) {
        mActivity.changeFragment(SearchMovieByTitleFragment.newInstance(listID, Constants.SEARCH_TYPE_MOVIES_BY_TITLE))
    }

    override fun openSearchByGenreScreen(listID: Int) {
        mActivity.changeFragment(SearchMovieByGenreFragment.newInstance(listID, Constants.SEARCH_TYPE_MOVIES_BY_GENRE))
    }

    override fun openLatestSearchScreen(listID: Int) {
        mActivity.changeFragment(SearchPopularMovieFragment.newInstance(listID, Constants.SEARCH_TYPE_LATEST_MOVIES))
    }

    override fun openPopularSearchScreen(listID: Int) {
        mActivity.changeFragment(SearchLatestMovieFragment.newInstance(listID, Constants.SEARCH_TYPE_POPULAR_MOVIES))
    }

    override fun closeFragment() {
        mActivity.onBackPressed()
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
//        super.onPrepareOptionsMenu(menu)
//        RxMenuItem.clicks(menu.findItem(R.id.menuDelete))
//                .throttleFirst(Constants.CLICK_DELAY, TimeUnit.MILLISECONDS)
//                .subscribe { o -> mPresenter.menuPressed() }
    }

    override fun showAlert() {
        val builder = AlertDialog.Builder(activity)
        builder.setMessage(R.string.question_about_goal)
        builder.setPositiveButton(R.string.answer_yes) { _, _ -> mPresenter.deleteList(mListID) }
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

    override fun closeFabMenu() {
        setClickableViews(false)
        fabAdd_VC.setImageResource(R.drawable.ic_add)
        seAnimation(mAnimFabClose!!)
        updateContainerAlpha(1f)
    }

    override fun openFabMenu() {
        fabAdd_VC.setImageResource(R.drawable.ic_close)
        setClickableViews(true)
        seAnimation(mAnimFabOpen!!)
        updateContainerAlpha(0.15f)
    }

    private fun updateContainerAlpha(value: Float) {
        if (rlPlaceholder_VC.visibility == View.VISIBLE)
            rlPlaceholder_VC.alpha = value
        else
            flContent_VC.alpha = value
    }

    private fun setClickableViews(isViewsClickable: Boolean) {
        fabFindUsingTitle.isClickable = isViewsClickable
        fabFindUsingGenre.isClickable = isViewsClickable
        fabFindPopular.isClickable = isViewsClickable
        fabFindLatest.isClickable = isViewsClickable
    }

    private fun seAnimation(animation: Animation) {
        llFindUsingTitle.startAnimation(animation)
        llFindUsingGenre.startAnimation(animation)
        llFindPopular.startAnimation(animation)
        llFindLatest.startAnimation(animation)
    }

}