package ua.shtain.irina.moviedbkt.view.screens.home.common.tv_show_details.tv_show_videos

import io.reactivex.disposables.CompositeDisposable
import ua.shtain.irina.moviedbkt.view.screens.home.common.videos.VideosContract
import ua.shtain.irina.moviedbkt.view.screens.home.common.videos.VideosPresenter
import javax.inject.Inject

/**
 * Created by Irina Shtain on 27.03.2018.
 */
class TvShowVideosPresenter @Inject constructor(compositeDisposable: CompositeDisposable,
                                                model: VideosContract.Model) : VideosPresenter() {
    init {
        mModel = model
        mCompositeDisposable = compositeDisposable
    }

    override fun getVideos(mID: Int) = mModel.getTvshowVideos(mID)
}