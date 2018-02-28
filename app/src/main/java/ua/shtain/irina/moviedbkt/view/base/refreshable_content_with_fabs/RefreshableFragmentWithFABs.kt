package ua.shtain.irina.moviedbkt.view.base.refreshable_content_with_fabs

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.jakewharton.rxbinding2.view.RxView
import kotlinx.android.synthetic.main.view_content_refreshable.*
import kotlinx.android.synthetic.main.view_placeholder.*
import ua.shtain.irina.moviedbkt.R
import ua.shtain.irina.moviedbkt.other.Constants
import ua.shtain.irina.moviedbkt.view.base.refresheble_content.RefreshableFragment
import ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.search.latest_movies.SearchLatestMovieFragment
import ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.search.popular_movies.SearchPopularMovieFragment
import ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.search.search_by_genre.SearchMovieByGenreFragment
import ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.search.search_by_title.SearchMovieByTitleFragment
import java.util.concurrent.TimeUnit

/**
 * Created by Irina Shtain on 27.02.2018.
 */
abstract class RefreshableFragmentWithFABs : RefreshableFragment(), RefreshableViewWithFABs {

    abstract override fun getPresenter(): RefreshablePresenterWithFABs

    private var mAnimFabClose: Animation? = null
    private var mAnimFabOpen: Animation? = null

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAnimations()
        setupFABs()
    }


    protected fun setupFABs() {
        fabAdd_VC.visibility = View.VISIBLE
        RxView.clicks(fabAdd_VC)
                .throttleFirst(Constants.CLICK_DELAY, TimeUnit.MILLISECONDS)
                .subscribe { getPresenter().onMainFABClick() }
        RxView.clicks(fabFindUsingTitle)
                .throttleFirst(Constants.CLICK_DELAY, TimeUnit.MILLISECONDS)
                .subscribe { getPresenter().onFabFindUsingTitleClick() }
        RxView.clicks(fabFindUsingGenre)
                .throttleFirst(Constants.CLICK_DELAY, TimeUnit.MILLISECONDS)
                .subscribe { getPresenter().onFabFindUsingGenreClick() }
        RxView.clicks(fabFindPopular)
                .throttleFirst(Constants.CLICK_DELAY, TimeUnit.MILLISECONDS)
                .subscribe { getPresenter().onFabFindPopularClick() }
        RxView.clicks(fabFindLatest)
                .throttleFirst(Constants.CLICK_DELAY, TimeUnit.MILLISECONDS)
                .subscribe { getPresenter().onFabFindLatestClick() }
    }

    private fun setupAnimations() {
        mAnimFabOpen = AnimationUtils.loadAnimation(activity, R.anim.menu_fab_open)
        mAnimFabClose = AnimationUtils.loadAnimation(activity, R.anim.menu_fab_close)
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