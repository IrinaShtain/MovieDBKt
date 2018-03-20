package ua.shtain.irina.moviedbkt.view.screens.home.common.movie_details

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.Toast
import com.jakewharton.rxbinding2.view.RxMenuItem
import com.jakewharton.rxbinding2.view.RxView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_movie_details.*
import ua.shtain.irina.moviedbkt.R
import ua.shtain.irina.moviedbkt.model.movie.MovieItem
import ua.shtain.irina.moviedbkt.model.movie.getGenres
import ua.shtain.irina.moviedbkt.other.Constants
import ua.shtain.irina.moviedbkt.view.base.IBasePresenter
import ua.shtain.irina.moviedbkt.view.base.content.ContentFragment
import ua.shtain.irina.moviedbkt.view.base.content.ContentView
import ua.shtain.irina.moviedbkt.view.screens.home.MainActivity
import ua.shtain.irina.moviedbkt.view.screens.home.common.movie_details.reviews.ReviewsFragment
import ua.shtain.irina.moviedbkt.view.screens.home.common.rating_dialog.RatingDialogFragment
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by Irina Shtain on 19.02.2018.
 */
class MovieDetailsFragment : ContentFragment(), MovieDetailsContract.View {

    private var mMovieID = 0
    private var mListID = 0
    private lateinit var mMovieTitle: String
    private lateinit var mMoviePosterPath: String
    private var dialogRating: RatingDialogFragment? = null

    @Inject
    lateinit var mPresenter: MovieDetailsPresenter

    override fun getLayoutRes() = R.layout.fragment_movie_details

    override fun getPresenter() = mPresenter as IBasePresenter<ContentView>

    override fun initGraph() {
        mActivity.mObjectGraph.getHomeComponent().inject(this)
    }

    companion object {
        private val MOVIE_ID = "movie_id"
        private val LIST_ID = "list_id"
        private val MOVIE_TITLE = "movie_title"
        private val MOVIE_POSTER = "movie_url"
        fun newInstance(movieID: Int, listID: Int, posterPath: String, title: String): MovieDetailsFragment {
            val fragment = MovieDetailsFragment()
            val bundle = Bundle()
            bundle.putInt(MOVIE_ID, movieID)
            bundle.putInt(LIST_ID, listID)
            bundle.putString(MOVIE_TITLE, title)
            bundle.putString(MOVIE_POSTER, posterPath)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mMovieID = arguments.getInt(MOVIE_ID)
        mListID = arguments.getInt(LIST_ID)
        mMovieTitle = arguments.getString(MOVIE_TITLE)
        mMoviePosterPath = arguments.getString(MOVIE_POSTER)
        initUI()
        setupTransitionElements()
        if (mListID == 0) fabAddToList.visibility = View.GONE
        mPresenter.mView = this
        mPresenter.subscribe()
    }

    private fun setupTransitionElements() {
        imageView.transitionName = mMovieTitle
        Picasso.with(context)
                .load(mMoviePosterPath)
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
        collapsingToolbar.title = mMovieTitle
        collapsingToolbar.setCollapsedTitleTextColor(Color.WHITE)
    }

    private fun initUI() {
        toolbar.setNavigationOnClickListener { mActivity.onBackPressed() }
        toolbar.inflateMenu(R.menu.menu_movie_details)
        RxView.clicks(fabAddToList)
                .throttleFirst(Constants.CLICK_DELAY, TimeUnit.MILLISECONDS)
                .subscribe { mPresenter.fabAddToListClicked(mListID) }
        RxView.clicks(fabRating)
                .throttleFirst(Constants.CLICK_DELAY, TimeUnit.MILLISECONDS)
                .subscribe { mPresenter.fabRatingClicked() }
        RxView.clicks(fabAddToFavorite)
                .throttleFirst(Constants.CLICK_DELAY, TimeUnit.MILLISECONDS)
                .subscribe { mPresenter.fabAddToFavoriteClicked() }
        RxView.clicks(fabAddToWatchList)
                .throttleFirst(Constants.CLICK_DELAY, TimeUnit.MILLISECONDS)
                .subscribe { mPresenter.fabAddToWatchListClicked() }

        RxMenuItem.clicks(toolbar.menu.getItem(0))
                .throttleFirst(Constants.CLICK_DELAY, TimeUnit.MILLISECONDS)
                .subscribe { mPresenter.menuReviewsPressed() }
        RxMenuItem.clicks(toolbar.menu.getItem(1))
                .throttleFirst(Constants.CLICK_DELAY, TimeUnit.MILLISECONDS)
                .subscribe { o -> Toast.makeText(context, "recommendations", Toast.LENGTH_LONG).show() }
        RxMenuItem.clicks(toolbar.menu.getItem(2))
                .throttleFirst(Constants.CLICK_DELAY, TimeUnit.MILLISECONDS)
                .subscribe { o -> Toast.makeText(context, "videos", Toast.LENGTH_LONG).show() }
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

    override fun setupUI(movieItem: MovieItem) {
        nsvContainer.visibility = View.VISIBLE
        tvType.text = resources.getString(R.string.type_movie)
        tvDescription.text = movieItem.overview
        tvTitle.text = movieItem.title
        tvGenre.text = resources.getString(R.string.genre, movieItem.getGenres())
        tvReleaseDate.text = movieItem.releaseDate
        tvPopularity.text = movieItem.voteAverage.toString()
    }

    override fun showRatingDialog() {
        dialogRating = RatingDialogFragment.newInstance(mMovieID, Constants.MEDIA_TYPE_MOVIE)
        dialogRating!!.setTargetFragment(this, Constants.REQUEST_CODE_RATE_MOVIE)
        dialogRating!!.show(mActivity.supportFragmentManager, "rate_ movie")
    }

    override fun getMovieID() = mMovieID

    override fun showReviews() {
        mActivity.changeFragment(ReviewsFragment.newInstance(mMovieID, mMovieTitle))
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