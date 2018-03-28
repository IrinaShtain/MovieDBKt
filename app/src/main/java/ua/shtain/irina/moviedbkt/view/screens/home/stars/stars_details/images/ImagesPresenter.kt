package ua.shtain.irina.moviedbkt.view.screens.home.stars.stars_details.images

import io.reactivex.disposables.CompositeDisposable
import ua.shtain.irina.moviedbkt.model.exceptions.ConnectionException
import ua.shtain.irina.moviedbkt.other.Constants
import javax.inject.Inject

/**
 * Created by Irina Shtain on 28.03.2018.
 */
class ImagesPresenter @Inject constructor(compositeDisposable: CompositeDisposable,
                                          model: ImagesContract.Model) : ImagesContract.Presenter {

    lateinit var mView: ImagesContract.View
    private var mID = 0

    private var mCompositeDisposable = compositeDisposable
    private var mModel = model

    override fun setView(view: ImagesContract.View) {
        mView = view
    }

    override fun subscribe() {
        mID = mView.getID()
        mView.showProgressMain()
        mCompositeDisposable.add(mModel.getStarImages(mID)
                .subscribe({ response ->
                    mView.hideProgress()
                    if (!response.imagesProfile.isEmpty())
                        mView.setImages(response.imagesProfile.mapTo(ArrayList()) { Constants.IMAGE_BASE + it.filePath })
                    else
                        mView.showPlaceholder(Constants.PlaceholderType.EMPTY)

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