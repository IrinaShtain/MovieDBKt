package ua.shtain.irina.moviedbkt.view.base.toolbar

import android.support.design.widget.FloatingActionButton
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import com.jakewharton.rxbinding2.view.RxView
import ua.shtain.irina.moviedbkt.R
import ua.shtain.irina.moviedbkt.other.Constants
import ua.shtain.irina.moviedbkt.view.base.BaseActivity
import ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.search.latest_movies.SearchLatestMovieFragment
import ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.search.popular_movies.SearchPopularMovieFragment
import ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.search.search_by_genre.SearchMovieByGenreFragment
import ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.search.search_by_title.SearchMovieByTitleFragment
import java.util.concurrent.TimeUnit

/**
 * Created by Irina Shtain on 27.02.2018.
 */
class FABManager constructor(activity: BaseActivity) {

    private var mActivity = activity

    private lateinit var mFabAdd: FloatingActionButton
    private lateinit var mLlFindUsingTitle: LinearLayout
    private lateinit var mLlFindUsingGenre: LinearLayout
    private lateinit var mLlFindPopular: LinearLayout
    private lateinit var mLlFindLatest: LinearLayout


    private var mIsFabOpen = false
    private var listID: Int = 0
    private var mAnimFabClose: Animation? = null
    private var mAnimFabOpen: Animation? = null

    fun attachFabAdd(fab: FloatingActionButton) {
        mFabAdd = fab
    }

    fun attachContainerFindUsingTitle(ll: LinearLayout) {
        mLlFindUsingTitle = ll
    }

    fun attachContainerFindUsingGenre(ll: LinearLayout) {
        mLlFindUsingGenre = ll
    }

    fun attachContainerFindPopular(ll: LinearLayout) {
        mLlFindPopular = ll
    }

    fun attachContainerFindLatest(ll: LinearLayout) {
        mLlFindLatest = ll
    }

    fun attachListID(listID: Int) {
        this.listID = listID
    }

    fun isFabGroupOpen() = mIsFabOpen

    fun showFabMenu(needFabMenu: Boolean) {
        if (needFabMenu) setupFABs()
    }

    private fun setupFABs() {
        setupAnimations()
        mFabAdd.visibility = View.VISIBLE
        RxView.clicks(mFabAdd)
                .throttleFirst(Constants.CLICK_DELAY, TimeUnit.MILLISECONDS)
                .subscribe { onMainFABClick() }
        RxView.clicks(mLlFindUsingTitle)
                .throttleFirst(Constants.CLICK_DELAY, TimeUnit.MILLISECONDS)
                .subscribe { onFabFindUsingTitleClick() }
        RxView.clicks(mLlFindUsingGenre)
                .throttleFirst(Constants.CLICK_DELAY, TimeUnit.MILLISECONDS)
                .subscribe { onFabFindUsingGenreClick() }
        RxView.clicks(mLlFindPopular)
                .throttleFirst(Constants.CLICK_DELAY, TimeUnit.MILLISECONDS)
                .subscribe { onFabFindPopularClick() }
        RxView.clicks(mLlFindLatest)
                .throttleFirst(Constants.CLICK_DELAY, TimeUnit.MILLISECONDS)
                .subscribe { onFabFindLatestClick() }
    }

    private fun onMainFABClick() {
        mIsFabOpen = if (mIsFabOpen) {
            closeFabMenu()
            false
        } else {
            openFabMenu()
            true
        }
    }

    private fun onFabFindUsingTitleClick() {
        mIsFabOpen = false
        openSearchByTitleScreen(listID)
    }

    private fun onFabFindUsingGenreClick() {
        mIsFabOpen = false
        openSearchByGenreScreen(listID)
    }

    private fun onFabFindPopularClick() {
        mIsFabOpen = false
        openPopularSearchScreen(listID)
    }

    private fun onFabFindLatestClick() {
        mIsFabOpen = false
        openLatestSearchScreen(listID)
    }

    private fun setupAnimations() {
        mAnimFabOpen = AnimationUtils.loadAnimation(mActivity, R.anim.menu_fab_open)
        mAnimFabClose = AnimationUtils.loadAnimation(mActivity, R.anim.menu_fab_close)
    }


    private fun openSearchByTitleScreen(listID: Int) {
        mActivity.changeFragment(SearchMovieByTitleFragment.newInstance(listID, Constants.SEARCH_TYPE_MOVIES_BY_TITLE))
    }

    private fun openSearchByGenreScreen(listID: Int) {
        mActivity.changeFragment(SearchMovieByGenreFragment.newInstance(listID, Constants.SEARCH_TYPE_MOVIES_BY_GENRE))
    }

    private fun openLatestSearchScreen(listID: Int) {
        mActivity.changeFragment(SearchPopularMovieFragment.newInstance(listID, Constants.SEARCH_TYPE_LATEST_MOVIES))
    }

    private fun openPopularSearchScreen(listID: Int) {
        mActivity.changeFragment(SearchLatestMovieFragment.newInstance(listID, Constants.SEARCH_TYPE_POPULAR_MOVIES))
    }

    fun closeFabMenu() {
        setClickableViews(false)
        mFabAdd.setImageResource(R.drawable.ic_add)
        setAnimation(mAnimFabClose!!)
        mIsFabOpen = false
        updateContainerAlpha(1f)
    }

    private fun openFabMenu() {
        mFabAdd.setImageResource(R.drawable.ic_close)
        setClickableViews(true)
        setAnimation(mAnimFabOpen!!)
        mIsFabOpen = true
        updateContainerAlpha(0.15f)
    }

    private fun updateContainerAlpha(value: Float) {
//        if (rlPlaceholder_VC.visibility == View.VISIBLE)
//            rlPlaceholder_VC.alpha = value
//        else
//            flContent_VC.alpha = value
    }

    private fun setClickableViews(isViewsClickable: Boolean) {
        mLlFindUsingTitle.isClickable = isViewsClickable
        mLlFindUsingGenre.isClickable = isViewsClickable
        mLlFindPopular.isClickable = isViewsClickable
        mLlFindLatest.isClickable = isViewsClickable
    }

    private fun setAnimation(animation: Animation) {
        mLlFindUsingTitle.startAnimation(animation)
        mLlFindUsingGenre.startAnimation(animation)
        mLlFindPopular.startAnimation(animation)
        mLlFindLatest.startAnimation(animation)
    }

}