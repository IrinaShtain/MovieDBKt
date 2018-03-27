package ua.shtain.irina.moviedbkt.view.screens.home.common.movie_details.movie_videos

import io.reactivex.disposables.CompositeDisposable
import ua.shtain.irina.moviedbkt.view.screens.home.common.videos.VideosContract
import ua.shtain.irina.moviedbkt.view.screens.home.common.videos.VideosPresenter
import javax.inject.Inject

/**
 * Created by Irina Shtain on 27.03.2018.
 */
class MovieVideosPresenter @Inject constructor(compositeDisposable: CompositeDisposable,
                                               model: VideosContract.Model) : VideosPresenter() {
    init {
        mModel = model
        mCompositeDisposable = compositeDisposable
    }

    override fun getVideos(mID: Int) = mModel.getMovieVideos(mID)
}