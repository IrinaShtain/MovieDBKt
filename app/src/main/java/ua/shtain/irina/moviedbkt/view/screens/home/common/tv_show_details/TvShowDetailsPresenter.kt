package ua.shtain.irina.moviedbkt.view.screens.home.common.tv_show_details

import io.reactivex.disposables.CompositeDisposable
import ua.shtain.irina.moviedbkt.model.exceptions.ConnectionException
import ua.shtain.irina.moviedbkt.model.tv.TvShowItem
import ua.shtain.irina.moviedbkt.other.Constants
import javax.inject.Inject

/**
 * Created by Irina Shtain on 01.03.2018.
 */
class TvShowDetailsPresenter @Inject constructor(compositeDisposable: CompositeDisposable,
                                                 model: TvShowDetailsContract.Model) : TvShowDetailsContract.Presenter {
    lateinit var mView: TvShowDetailsContract.View
    private var tvID = 0

    private var mCompositeDisposable = compositeDisposable
    private var mModel = model
    private var tvShowItem: TvShowItem? = null

    override fun setView(view: TvShowDetailsContract.View) {
        mView = view
    }

    override fun subscribe() {
        tvID = mView.getTvID()
        mView.showProgressPagination()
        mCompositeDisposable.add(mModel.getTvDetails(tvID)
                .subscribe({ response ->
                    tvShowItem = response
                    mView.hideProgress()
                    mView.setupUI(response)
                }, { throwable ->
                    mView.hideProgress()
                    throwable.printStackTrace()
                    if (throwable is ConnectionException) {
                        mView.showMessage(Constants.MessageType.CONNECTION_PROBLEMS)
                    } else {
                        mView.showMessage(Constants.MessageType.UNKNOWN)
                    }
                }))
    }

    override fun unsubscribe() {
        mCompositeDisposable.clear()
    }


    override fun fabAddToFavoriteClicked() {
        mCompositeDisposable.add(mModel.addToFavoriteTV(tvID)
                .subscribe({ (_, _, _) ->
                    mView.hideProgress()
                    mView.showMessage(Constants.MessageType.NEW_MOVIE_ADDED_SUCCESSFULLY)
                }, throwableConsumer))
    }

    override fun fabAddToWatchListClicked() {
        mCompositeDisposable.add(mModel.addToWatchListTV(tvID)
                .subscribe({
                    mView.hideProgress()
                    mView.showMessage(Constants.MessageType.NEW_MOVIE_ADDED_SUCCESSFULLY)
                }, throwableConsumer))
    }

    override fun fabRatingClicked() {
        mView.showRatingDialog()
    }

    override fun showResult(errorCode: Int) {
        when (errorCode) {
            Constants.ERROR_CODE_CONNECTION_LOST -> mView.showMessage(Constants.MessageType.CONNECTION_PROBLEMS)
            Constants.ERROR_CODE_UNKNOWN -> mView.showMessage(Constants.MessageType.UNKNOWN)
            else -> mView.showMessage(Constants.MessageType.MOVIE_RATED_SUCCESSFULLY)
        }
    }

    override fun menuReviewsPressed() {
        mView.showReviews()
    }

    override fun menuRecommendedMoviesPressed() {
        mView.showRecommendedMovies()
    }

    override fun menuVideosMoviesPressed() {
        mView.showVideos()
    }

    private val throwableConsumer = { throwable: Throwable ->
        throwable.printStackTrace()
        mView.hideProgress()
        if (throwable is ConnectionException) {
            mView.showMessage(Constants.MessageType.CONNECTION_PROBLEMS)
        } else {
            mView.showMessage(Constants.MessageType.UNKNOWN)
        }
    }
}