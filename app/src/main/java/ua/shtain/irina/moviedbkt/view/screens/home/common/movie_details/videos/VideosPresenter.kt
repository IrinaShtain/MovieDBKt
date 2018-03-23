package ua.shtain.irina.moviedbkt.view.screens.home.common.movie_details.videos

import android.util.Log
import io.reactivex.disposables.CompositeDisposable
import ua.shtain.irina.moviedbkt.model.exceptions.ConnectionException
import ua.shtain.irina.moviedbkt.model.movie.videos.VideosItem
import ua.shtain.irina.moviedbkt.other.Constants
import ua.shtain.irina.moviedbkt.view.base.content.ContentView
import ua.shtain.irina.moviedbkt.view.screens.home.common.movie_details.videos.adapter.VideoItemDH
import java.util.*
import javax.inject.Inject

/**
 * Created by Irina Shtain on 22.03.2018.
 */
class VideosPresenter @Inject constructor(compositeDisposable: CompositeDisposable,
                                          model: VideosContract.Model) : VideosContract.Presenter {
    lateinit var mView: VideosContract.View
    private var mCompositeDisposable = compositeDisposable
    private var mModel = model
    private var movieID = 0
    private var hasData = false

    private val throwableConsumer = { throwable: Throwable ->
        Log.d("myLogs", "Error! " + throwable.message)
        throwable.printStackTrace()
        mView.hideProgress()
        if (hasData)
            if (throwable is ConnectionException) {
                mView.showMessage(Constants.MessageType.CONNECTION_PROBLEMS)
            } else {
                mView.showMessage(Constants.MessageType.UNKNOWN)
            }
        else if (throwable is ConnectionException) {
            mView.showPlaceholder(Constants.PlaceholderType.NETWORK)
        } else {
            mView.showPlaceholder(Constants.PlaceholderType.UNKNOWN)
        }
    }

    override fun setView(view: ContentView) {
        mView = view as VideosContract.View
    }

    override fun subscribe() {
        movieID = mView.getMovieID()
        mView.showProgressMain()
        loadVideosInfo()
    }

    override fun unsubscribe() {
        mCompositeDisposable.clear()
    }

    override fun onRefresh() {
        loadVideosInfo()
    }

    override fun videoItemPressed(url: String) {
        mView.openVideo(url)
    }

    private fun loadVideosInfo() {
        mCompositeDisposable.add(
                mModel.getVideos(movieID)
                        .subscribe({ response ->
                            mView.hideProgress()
                            hasData = true
                            if (response.videos.isEmpty())
                                mView.showPlaceholder(Constants.PlaceholderType.EMPTY)
                            else
                                mView.setList(prepareList(response.videos))

                        }, throwableConsumer))
    }

    private fun prepareList(items: ArrayList<VideosItem>): ArrayList<VideoItemDH> {
        return items.mapTo(ArrayList()) { VideoItemDH(it) }
    }
}