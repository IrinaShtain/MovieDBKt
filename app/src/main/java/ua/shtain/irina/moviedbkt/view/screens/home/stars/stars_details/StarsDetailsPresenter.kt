package ua.shtain.irina.moviedbkt.view.screens.home.stars.stars_details

import io.reactivex.disposables.CompositeDisposable
import ua.shtain.irina.moviedbkt.model.exceptions.ConnectionException
import ua.shtain.irina.moviedbkt.model.star.StarDetails
import ua.shtain.irina.moviedbkt.model.star.getPosterUrl
import ua.shtain.irina.moviedbkt.other.Constants
import javax.inject.Inject

/**
 * Created by Irina Shtain on 01.03.2018.
 */
class StarsDetailsPresenter @Inject constructor(compositeDisposable: CompositeDisposable,
                                                model: StarsDetailsContract.Model) : StarsDetailsContract.Presenter {
    lateinit var mView: StarsDetailsContract.View
    private var personID = 0

    private var mCompositeDisposable = compositeDisposable
    private var mModel = model
    private var star: StarDetails? = null


    override fun setView(view: StarsDetailsContract.View) {
        mView = view
    }

    override fun subscribe() {
        if (star == null) {
            personID = mView.getPersonID()
            mView.showProgressPagination()
            mCompositeDisposable.add(mModel.getStarDetails(personID)
                    .subscribe({ response ->
                        star = response
                        mView.hideProgress()
                        mView.setupFamousFor()
                        mView.setStarRating(response.popularity.toString())
                        mView.setStarBiography(response.biography)
                    }, { throwable ->
                        mView.hideProgress()
                        throwable.printStackTrace()
                        if (throwable is ConnectionException) {
                            mView.showMessage(Constants.MessageType.CONNECTION_PROBLEMS)
                        } else {
                            mView.showMessage(Constants.MessageType.UNKNOWN)
                        }
                    }))
        } else {
            mView.setupFamousFor()
            mView.setStarRating(star!!.popularity.toString())
            mView.setStarBiography(star!!.biography)
        }

    }

    override fun unsubscribe() {
        mCompositeDisposable.clear()
    }
}