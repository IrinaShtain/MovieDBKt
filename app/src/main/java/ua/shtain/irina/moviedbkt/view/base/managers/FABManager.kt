package ua.shtain.irina.moviedbkt.view.base.toolbar

import android.support.design.widget.FloatingActionButton
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import android.widget.TextView
import com.jakewharton.rxbinding2.view.RxView
import ua.shtain.irina.moviedbkt.R
import ua.shtain.irina.moviedbkt.other.Constants
import ua.shtain.irina.moviedbkt.view.base.BaseActivity
import ua.shtain.irina.moviedbkt.view.screens.home.common.search_movies.latest_movies.SearchLatestMovieFragment
import ua.shtain.irina.moviedbkt.view.screens.home.common.search_movies.popular_movies.SearchPopularMovieFragment
import ua.shtain.irina.moviedbkt.view.screens.home.common.search_movies.search_by_genre.SearchMovieByGenreFragment
import ua.shtain.irina.moviedbkt.view.screens.home.common.search_movies.search_by_title.SearchMovieByTitleFragment
import ua.shtain.irina.moviedbkt.view.screens.home.common.search_shows.latest_shows.SearchLatestTvShowsFragment
import ua.shtain.irina.moviedbkt.view.screens.home.common.search_shows.on_air_shows.SearchOnAirTvShowsFragment
import ua.shtain.irina.moviedbkt.view.screens.home.common.search_shows.popular_shows.SearchPopularTvShowsFragment
import ua.shtain.irina.moviedbkt.view.screens.home.common.search_shows.top_rated_shows.SearchTopRatedTvShowsFragment
import java.util.concurrent.TimeUnit

/**
 * Created by Irina Shtain on 27.02.2018.
 */
class FABManager constructor(activity: BaseActivity) {

    private var mActivity = activity

    private lateinit var mFabAdd: FloatingActionButton
    private lateinit var mTvFindPopular: TextView
    private lateinit var mTvFindLatest: TextView
    private lateinit var mTvFindUsingTitleOrTopRated: TextView
    private lateinit var mTvFindUsingGenreOrOnAir: TextView
    private lateinit var mLlFindUsingTitle: LinearLayout
    private lateinit var mLlFindUsingGenre: LinearLayout
    private lateinit var mLlFindPopular: LinearLayout
    private lateinit var mLlFindLatest: LinearLayout


    private var mIsFabOpen = false
    private var mAreMovies = true
    private var listID: Int = 0
    private var mAnimFabClose: Animation? = null
    private var mAnimFabOpen: Animation? = null

    fun attachFabAdd(fab: FloatingActionButton) {
        mFabAdd = fab
    }

    fun attachContainerFindUsingTitle(ll: LinearLayout) {
        mLlFindUsingTitle = ll
        mTvFindUsingTitleOrTopRated = ll.findViewById(R.id.tvFindUsingTitle)
    }

    fun attachContainerFindUsingGenre(ll: LinearLayout) {
        mLlFindUsingGenre = ll
        mTvFindUsingGenreOrOnAir = ll.findViewById(R.id.tvFindUsingGenre)
    }

    fun attachContainerFindPopular(ll: LinearLayout) {
        mLlFindPopular = ll
        mTvFindPopular = ll.findViewById(R.id.tvFindPopular)
    }

    fun attachContainerFindLatest(ll: LinearLayout) {
        mLlFindLatest = ll
        mTvFindLatest = ll.findViewById(R.id.tvFindLatest)
    }

    fun attachListID(listID: Int) {
        this.listID = listID
    }

    fun isFabGroupOpen() = mIsFabOpen

    fun showFabMenu(needFabMenu: Boolean, areMovies: Boolean = true) {
        mAreMovies = areMovies
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
        when {
            mAreMovies -> {
                mTvFindLatest.text = mActivity.getString(R.string.menu_fab_latest_movies)
                mTvFindPopular.text = mActivity.getString(R.string.menu_fab_popular_movies)
                mTvFindUsingGenreOrOnAir.text = mActivity.getString(R.string.menu_fab_find_by_genres)
                mTvFindUsingTitleOrTopRated.text = mActivity.getString(R.string.menu_fab_find_by_title)
            }
            else -> {
                mTvFindLatest.text = mActivity.getString(R.string.menu_fab_latest_shows)
                mTvFindPopular.text = mActivity.getString(R.string.menu_fab_popular_shows)
                mTvFindUsingGenreOrOnAir.text = mActivity.getString(R.string.menu_fab_currently_on_the_air_shows)
                mTvFindUsingTitleOrTopRated.text = mActivity.getString(R.string.menu_fab_top_rated_shows)
            }
        }
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
        openSearchByTitleOrTopRatedScreen(listID)
    }

    private fun onFabFindUsingGenreClick() {
        mIsFabOpen = false
        openSearchByGenreOrOnAirScreen(listID)
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


    private fun openSearchByTitleOrTopRatedScreen(listID: Int) {
        when {
            mAreMovies -> mActivity.changeFragment(SearchMovieByTitleFragment.newInstance(listID))
            else -> mActivity.changeFragment(SearchTopRatedTvShowsFragment())
        }
    }

    private fun openSearchByGenreOrOnAirScreen(listID: Int) {
        if (mAreMovies)
            mActivity.changeFragment(SearchMovieByGenreFragment.newInstance(listID))
        else
            mActivity.changeFragment(SearchOnAirTvShowsFragment())
    }

    private fun openLatestSearchScreen(listID: Int) {
        when {
            mAreMovies -> mActivity.changeFragment(SearchPopularMovieFragment.newInstance(listID))
            else -> mActivity.changeFragment(SearchLatestTvShowsFragment())
        }

    }

    private fun openPopularSearchScreen(listID: Int) {
        when {
            mAreMovies -> mActivity.changeFragment(SearchLatestMovieFragment.newInstance(listID))
            else -> mActivity.changeFragment(SearchPopularTvShowsFragment())
        }
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
