package ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.movie_details

import android.util.Log
import io.reactivex.disposables.CompositeDisposable
import ua.shtain.irina.moviedbkt.model.exceptions.ConnectionException
import ua.shtain.irina.moviedbkt.model.movie.FavoriteRequest
import ua.shtain.irina.moviedbkt.model.movie.MovieItem
import ua.shtain.irina.moviedbkt.other.Constants
import javax.inject.Inject

/**
 * Created by Irina Shtain on 19.02.2018.
 */
class MovieDetailsPresenter @Inject constructor(compositeDisposable: CompositeDisposable,
                                                model: MovieDetailsContract.Model) : MovieDetailsContract.Presenter {
    lateinit var mView: MovieDetailsContract.View
    private var movieID = 0

    private var mCompositeDisposable = compositeDisposable
    private var mModel = model
    private var movieItem: MovieItem? = null

    override fun setView(view: MovieDetailsContract.View) {
        mView = view
    }

    override fun subscribe() {
        movieID = mView.getMovieID()
        mView.showProgressMain()
        mCompositeDisposable.add(mModel.getMovieDetails(movieID)
                .subscribe({ response ->
                    movieItem = response
                    mView.hideProgress()
                    mView.setupUI(response)
                    mView.setupButton(false)
                }, { throwable ->
                    mView.hideProgress()
                    throwable.printStackTrace()
                    if (throwable is ConnectionException) {
                        mView.showPlaceholder(Constants.PlaceholderType.NETWORK)
                    } else {
                        mView.showPlaceholder(Constants.PlaceholderType.UNKNOWN)
                    }
                }))
    }

    override fun unsubscribe() {
        mCompositeDisposable.clear()
    }

    override fun fabAddToFavoriteClicked() {
        mCompositeDisposable.add(mModel.addToFavoriteMovie(movieID)
                .subscribe({ (_, _, _) ->
                    mView.hideProgress()
                    mView.showMessage(Constants.MessageType.NEW_MOVIE_ADDED_SUCCESSFULLY)
                }, throwableConsumer))
    }

    override fun fabAddToWatchListClicked() {
        mCompositeDisposable.add(mModel.addToWatchListMovie(movieID)
                .subscribe({ (_, _, _) ->
                    mView.hideProgress()
                    mView.showMessage(Constants.MessageType.NEW_MOVIE_ADDED_SUCCESSFULLY)
                }, throwableConsumer))
    }

    override fun fabAddToListClicked(listID: Int) {
        mCompositeDisposable.add(mModel.addToListMovie(listID, movieID)
                .subscribe({ (_, _, _) ->
                    mView.hideProgress()
                    mView.showMessage(Constants.MessageType.NEW_MOVIE_ADDED_SUCCESSFULLY)
                    mView.setupButton(true)
                }, { throwable: Throwable ->
                    throwable.printStackTrace()
                    mView.hideProgress()
                    when {
                        throwable is ConnectionException -> mView.showMessage(Constants.MessageType.CONNECTION_PROBLEMS)
                        throwable.message.equals("HTTP 403 Forbidden") -> mView.showMessage(Constants.MessageType.MOVIE_ALREADY_ADDED)
                        else -> mView.showMessage(Constants.MessageType.UNKNOWN)
                    }

                }))
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