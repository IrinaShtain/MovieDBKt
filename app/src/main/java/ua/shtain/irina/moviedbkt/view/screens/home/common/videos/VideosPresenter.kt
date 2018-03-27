package ua.shtain.irina.moviedbkt.view.screens.home.common.videos

import android.util.Log
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import ua.shtain.irina.moviedbkt.model.exceptions.ConnectionException
import ua.shtain.irina.moviedbkt.model.movie.videos.VideosItem
import ua.shtain.irina.moviedbkt.model.movie.videos.VideosResponse
import ua.shtain.irina.moviedbkt.other.Constants
import ua.shtain.irina.moviedbkt.view.base.content.ContentView
import ua.shtain.irina.moviedbkt.view.screens.home.common.videos.adapter.VideoItemDH
import java.util.*

/**
 * Created by Irina Shtain on 22.03.2018.
 */
abstract class VideosPresenter : VideosContract.Presenter {
    lateinit var mView: VideosContract.View
    protected lateinit var mCompositeDisposable: CompositeDisposable
    protected lateinit var mModel: VideosContract.Model
    protected var mID = 0
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

    abstract fun getVideos(mID: Int): Observable<VideosResponse>

    override fun setView(view: ContentView) {
        mView = view as VideosContract.View
    }

    override fun subscribe() {
        mID = mView.getMovieID()
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
        mCompositeDisposable.add(getVideos(mID)
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