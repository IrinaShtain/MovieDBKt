package ua.shtain.irina.moviedbkt.view.screens.home.stars.stars_details

import io.reactivex.disposables.CompositeDisposable
import ua.shtain.irina.moviedbkt.model.exceptions.ConnectionException
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


    override fun setView(view: StarsDetailsContract.View) {
        mView = view
    }

    override fun subscribe() {
        personID = mView.getPersonID()
        mView.showProgressMain()
        mCompositeDisposable.add(mModel.getStarDetails(personID)
                .subscribe({ response ->
                    mView.hideProgress()
                    mView.setupFamousFor()
                    mView.setStarName(response.name)
                    mView.setStarRating(response.popularity.toString())
                    mView.setStarBiography(response.biography)
                    mView.setStarImage(response.getPosterUrl())
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
}