package ua.shtain.irina.moviedbkt.view.screens.home.common.tv_show_details

import io.reactivex.Observable
import ua.shtain.irina.moviedbkt.model.lists.ResponseMessage
import ua.shtain.irina.moviedbkt.model.tv.TvShowItem
import ua.shtain.irina.moviedbkt.view.base.IBasePresenter
import ua.shtain.irina.moviedbkt.view.base.content.ContentView

/**
 * Created by Irina Shtain on 01.03.2018.
 */
interface TvShowDetailsContract {
    interface View : ContentView {
        fun setupUI(tvShowItem: TvShowItem)
        fun showRatingDialog()
        fun getTvID(): Int
    }

    interface Presenter : IBasePresenter<View> {
        fun fabRatingClicked()
        fun fabAddToFavoriteClicked()
        fun fabAddToWatchListClicked()
        fun showResult(errorCode: Int)
    }

    interface Model {
        fun getTvDetails(tvID: Int): Observable<TvShowItem>
        fun addToFavoriteTV(showID: Int): Observable<ResponseMessage>
        fun addToWatchListTV(showID: Int): Observable<ResponseMessage>
    }
}