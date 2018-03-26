package ua.shtain.irina.moviedbkt.view.screens.home.common.tv_show_details

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v4.content.ContextCompat
import android.view.View
import com.jakewharton.rxbinding2.view.RxMenuItem
import com.jakewharton.rxbinding2.view.RxView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_tv_details.*
import ua.shtain.irina.moviedbkt.R
import ua.shtain.irina.moviedbkt.model.tv.TvShowItem
import ua.shtain.irina.moviedbkt.model.tv.getGenres
import ua.shtain.irina.moviedbkt.other.Constants
import ua.shtain.irina.moviedbkt.view.base.IBasePresenter
import ua.shtain.irina.moviedbkt.view.base.content.ContentFragment
import ua.shtain.irina.moviedbkt.view.base.content.ContentView
import ua.shtain.irina.moviedbkt.view.screens.home.MainActivity
import ua.shtain.irina.moviedbkt.view.screens.home.common.rating_dialog.RatingDialogFragment
import ua.shtain.irina.moviedbkt.view.screens.home.common.tv_show_details.recommendations.RecommendedTvShowsFragment
import ua.shtain.irina.moviedbkt.view.screens.home.common.tv_show_details.tv_show_reviews.TvShowReviewsFragment
import java.util.concurrent.TimeUnit
import javax.inject.Inject


/**
 * Created by Irina Shtain on 01.03.2018.
 */
class TvShowDetailsFragment : ContentFragment(), TvShowDetailsContract.View {

    private var mTvID = 0
    private lateinit var mTvTitle: String
    private lateinit var mTvPosterPath: String
    private var dialogRating: RatingDialogFragment? = null

    @Inject
    lateinit var mPresenter: TvShowDetailsPresenter

    override fun getLayoutRes() = R.layout.fragment_tv_details

    override fun getPresenter() = mPresenter as IBasePresenter<ContentView>

    override fun initGraph() {
        mActivity.mObjectGraph.getHomeComponent().inject(this)
    }

    companion object {
        private val TV_ID = "tv_id"
        private val TV_TITLE = "tv_title"
        private val TV_POSTER = "tv_url"
        fun newInstance(movieID: Int, posterPath: String, title: String): TvShowDetailsFragment {
            val fragment = TvShowDetailsFragment()
            val bundle = Bundle()
            bundle.putInt(TV_ID, movieID)
            bundle.putString(TV_TITLE, title)
            bundle.putString(TV_POSTER, posterPath)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mTvID = arguments.getInt(TV_ID)
        mTvTitle = arguments.getString(TV_TITLE)
        mTvPosterPath = arguments.getString(TV_POSTER)
        initUI()
        setupTransitionElements()
        mPresenter.mView = this
        mPresenter.subscribe()
    }

    private fun setupTransitionElements() {
        imageView.transitionName = mTvTitle
        Picasso.with(context)
                .load(mTvPosterPath)
                .fit()
                .noFade()
                .centerCrop()
                .placeholder(R.drawable.bg_title_black_gradient)
                .error(R.drawable.placeholder_movie)
                .into(imageView, object : Callback {
                    override fun onError() {
                        mActivity.supportStartPostponedEnterTransition()
                    }
                    override fun onSuccess() {
                        mActivity.supportStartPostponedEnterTransition()
                    }
                })
        collapsingToolbar.title = mTvTitle
        collapsingToolbar.setCollapsedTitleTextColor(Color.WHITE)
    }

    private fun initUI() {
        toolbar.setNavigationOnClickListener { mActivity.onBackPressed() }
        toolbar.inflateMenu(R.menu.menu_movie_details)
        RxView.clicks(fabRating)
                .throttleFirst(Constants.CLICK_DELAY, TimeUnit.MILLISECONDS)
                .subscribe { _ -> mPresenter.fabRatingClicked() }
        RxView.clicks(fabAddToFavorite)
                .throttleFirst(Constants.CLICK_DELAY, TimeUnit.MILLISECONDS)
                .subscribe { _ -> mPresenter.fabAddToFavoriteClicked() }
        RxView.clicks(fabAddToWatchList)
                .throttleFirst(Constants.CLICK_DELAY, TimeUnit.MILLISECONDS)
                .subscribe { _ -> mPresenter.fabAddToWatchListClicked() }

        RxMenuItem.clicks(toolbar.menu.getItem(0))
                .throttleFirst(Constants.CLICK_DELAY, TimeUnit.MILLISECONDS)
                .subscribe { mPresenter.menuReviewsPressed() }
        RxMenuItem.clicks(toolbar.menu.getItem(1))
                .throttleFirst(Constants.CLICK_DELAY, TimeUnit.MILLISECONDS)
                .subscribe { mPresenter.menuRecommendedMoviesPressed() }
        RxMenuItem.clicks(toolbar.menu.getItem(2))
                .throttleFirst(Constants.CLICK_DELAY, TimeUnit.MILLISECONDS)
                .subscribe { mPresenter.menuVideosMoviesPressed() }
        setupCollapsingToolbar()
    }

    private fun setupCollapsingToolbar() {
        (mActivity as MainActivity).getToolbarMan()?.displayToolbar(false)
        appBarLayout.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {
            internal var scrollRange = -1
            internal var collapseOffset = -1

            override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.totalScrollRange
                    collapseOffset = appBarLayout.height / 3
                }
                if (scrollRange + verticalOffset <= collapseOffset) {
                    toolbar.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary))
                } else {
                    toolbar.setBackgroundResource(R.drawable.bg_title_black_gradient)
                    toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white)
                }
            }
        })
    }

    override fun setupUI(tvShowItem: TvShowItem) {
        nsvInfoContainer.visibility = View.VISIBLE
        tvDescription.text = tvShowItem.overview
        tvNumberOfSeasons.text = resources.getString(R.string.number_of_seasons, tvShowItem.numberOfSeasons.toString())
        tvType.text = resources.getString(R.string.type_tv)
        tvTitle.text = tvShowItem.title
        tvGenre.text = resources.getString(R.string.genre, tvShowItem.getGenres())
        tvReleaseDate.text = tvShowItem.firstAirDate
        tvPopularity.text = tvShowItem.voteAverage.toString()
    }

    override fun showRatingDialog() {
        dialogRating = RatingDialogFragment.newInstance(mTvID, Constants.MEDIA_TYPE_TV)
        dialogRating!!.setTargetFragment(this, Constants.REQUEST_CODE_RATE_MOVIE)
        dialogRating!!.show(mActivity.supportFragmentManager, "rate_ tv_show")
    }

    override fun getTvID() = mTvID

    override fun showReviews() {
        mActivity.changeFragment(TvShowReviewsFragment.newInstance(mTvID, mTvTitle))
    }

    override fun showRecommendedMovies() {
        mActivity.changeFragment(RecommendedTvShowsFragment.newInstance(mTvID))
    }

    override fun showVideos() {
       // mActivity.changeFragment(VideosFragment.newInstance(mTvID, mTvTitle))
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == Constants.REQUEST_CODE_RATE_MOVIE) {
            mPresenter.showResult(data!!.getIntExtra(Constants.KEY_ERROR_CODE, Constants.ERROR_CODE_UNKNOWN))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        (mActivity as MainActivity).getToolbarMan()?.displayToolbar(true)
        if (dialogRating != null && dialogRating!!.isVisible)
            dialogRating!!.dismiss()
    }


}