package ua.shtain.irina.moviedbkt.view.screens.home.common.movie_details

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v4.content.ContextCompat
import android.view.View
import com.jakewharton.rxbinding2.view.RxView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_movie_details.*
import ua.shtain.irina.moviedbkt.R
import ua.shtain.irina.moviedbkt.model.movie.MovieItem
import ua.shtain.irina.moviedbkt.model.movie.getAvatarUrl
import ua.shtain.irina.moviedbkt.model.movie.getGenres
import ua.shtain.irina.moviedbkt.other.Constants
import ua.shtain.irina.moviedbkt.view.base.IBasePresenter
import ua.shtain.irina.moviedbkt.view.base.content.ContentFragment
import ua.shtain.irina.moviedbkt.view.base.content.ContentView
import ua.shtain.irina.moviedbkt.view.screens.home.MainActivity
import ua.shtain.irina.moviedbkt.view.screens.home.common.movie_details.rating_dialog.RatingDialogFragment
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by Irina Shtain on 19.02.2018.
 */
class MovieDetailsFragment : ContentFragment(), MovieDetailsContract.View {

    private var mMovieID = 0
    private var mListID = 0
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
        fun newInstance(movieID: Int, listID: Int): MovieDetailsFragment {
            val fragment = MovieDetailsFragment()
            val bundle = Bundle()
            bundle.putInt(MOVIE_ID, movieID)
            bundle.putInt(LIST_ID, listID)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mMovieID = arguments.getInt(MOVIE_ID)
        mListID = arguments.getInt(LIST_ID)
        initUI()
        if (mListID == 0) fabAddToList.visibility = View.GONE
        mPresenter.mView = this
        mPresenter.subscribe()
    }

    private fun initUI() {
        toolbar.setNavigationOnClickListener {mActivity.onBackPressed() }
        RxView.clicks(fabAddToList)
                .throttleFirst(Constants.CLICK_DELAY, TimeUnit.MILLISECONDS)
                .subscribe { _ -> mPresenter.fabAddToListClicked(mListID) }
        RxView.clicks(fabRating)
                .throttleFirst(Constants.CLICK_DELAY, TimeUnit.MILLISECONDS)
                .subscribe { _ -> mPresenter.fabRatingClicked() }
        RxView.clicks(fabAddToFavorite)
                .throttleFirst(Constants.CLICK_DELAY, TimeUnit.MILLISECONDS)
                .subscribe { _ -> mPresenter.fabAddToFavoriteClicked() }
        RxView.clicks(fabAddToWatchList)
                .throttleFirst(Constants.CLICK_DELAY, TimeUnit.MILLISECONDS)
                .subscribe { _ -> mPresenter.fabAddToWatchListClicked() }
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
        collapsingToolbar.title = movieItem.title
        collapsingToolbar.setCollapsedTitleTextColor(Color.WHITE)
        tv_description.text = movieItem.overview
        tvTitle.text = movieItem.title
        tv_genre.text = resources.getString(R.string.genre, movieItem.getGenres())
        tv_releaseDate.text = movieItem.releaseDate
        tvPopularity.text = movieItem.voteAverage.toString()
        Picasso.with(context)
                .load(movieItem.getAvatarUrl())
                .error(R.drawable.placeholder_movie)
                .into(imageView)

    }

    override fun showRatingDialog() {
        dialogRating = RatingDialogFragment.newInstance(mMovieID)
        dialogRating!!.setTargetFragment(this, Constants.REQUEST_CODE_RATE_MOVIE)
        dialogRating!!.show(mActivity.supportFragmentManager, "rate_ movie")
    }

    override fun getMovieID() = mMovieID

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