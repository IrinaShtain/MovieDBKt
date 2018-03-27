package ua.shtain.irina.moviedbkt.view.screens.home.common.videos

import io.reactivex.Observable
import ua.shtain.irina.moviedbkt.model.movie.videos.VideosResponse
import ua.shtain.irina.moviedbkt.view.base.refresheble_content.RefreshablePresenter
import ua.shtain.irina.moviedbkt.view.base.refresheble_content.RefreshableView
import ua.shtain.irina.moviedbkt.view.screens.home.common.videos.adapter.VideoItemDH

/**
 * Created by Irina Shtain on 22.03.2018.
 */
interface VideosContract {
    interface View : RefreshableView {
        fun setList(itemDHs: MutableList<VideoItemDH>)
        fun getMovieID(): Int
        fun openVideo(url : String)
    }

    interface Presenter : RefreshablePresenter {
        fun videoItemPressed(url : String)
    }

    interface Model {
        fun getMovieVideos(movieID: Int): Observable<VideosResponse>
        fun getTvshowVideos(tvShowID: Int): Observable<VideosResponse>
    }
}