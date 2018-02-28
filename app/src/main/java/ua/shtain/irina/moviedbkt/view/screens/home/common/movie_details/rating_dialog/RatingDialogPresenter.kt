package ua.shtain.irina.moviedbkt.view.screens.home.common.movie_details.rating_dialog

import android.util.Log
import io.reactivex.disposables.CompositeDisposable
import ua.shtain.irina.moviedbkt.model.exceptions.ConnectionException
import ua.shtain.irina.moviedbkt.other.Constants
import javax.inject.Inject

/**
 * Created by Irina Shtain on 28.02.2018.
 */
class RatingDialogPresenter @Inject constructor(compositeDisposable: CompositeDisposable,
                                                model: RatingDialogContract.Model) : RatingDialogContract.Presenter {
    lateinit var mView: RatingDialogContract.View
    private var movieID = 0

    private var mCompositeDisposable = compositeDisposable
    private var mModel = model
    override fun setView(view: RatingDialogContract.View) {
        mView = view
    }

    override fun subscribe() {
        movieID = mView.getMovieID()
    }

    override fun unsubscribe() {
        mCompositeDisposable.clear()
    }

    override fun sendRating(rating: Float) {
        if (rating <= 0)
            mView.showRatingError()
        else {
            mView.showProgress()
            mView.hideRatingError()
            mCompositeDisposable.add(mModel.rateMovie(rating, movieID)
                    .subscribe({ response ->
                        mView.hideProgress()
                        mView.updateTargetFragment(0)
                    }, { throwable ->
                        mView.hideProgress()
                        if (throwable is ConnectionException) {
                            mView.updateTargetFragment(Constants.ERROR_CODE_CONNECTION_LOST)
                        } else {
                            mView.updateTargetFragment(Constants.ERROR_CODE_UNKNOWN)
                        }
                        Log.e("myLog", "throwable " + throwable.message)
                    }))

        }
    }
}


