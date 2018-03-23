package ua.shtain.irina.moviedbkt.view.screens.home.common.tv_shows

import io.reactivex.Observable
import ua.shtain.irina.moviedbkt.model.lists.ResponseMessage
import ua.shtain.irina.moviedbkt.model.tv.SearchTvShowResponse
import ua.shtain.irina.moviedbkt.view.base.refresheble_content.RefreshablePresenter
import ua.shtain.irina.moviedbkt.view.base.refresheble_content.RefreshableView
import ua.shtain.irina.moviedbkt.view.screens.home.common.tv_shows.adapter.TvShowItemDH

/**
 * Created by Irina Shtain on 02.03.2018.
 */
interface TvShowsContract {
    interface View : RefreshableView {
        fun setList(showsDHs: MutableList<TvShowItemDH>)
        fun addList(showsDHs: MutableList<TvShowItemDH>)
        fun getShowsType(): Int
        fun getShowID(): Int
        fun showAlert(itemId: Int, position: Int)
        fun updateTvShows(position: Int)
    }

    interface Presenter : RefreshablePresenter {
        fun getNextPage()
        fun updateNeedRefresh(needRefresh: Boolean)
        fun deletionConfirmed(itemId: Int, position: Int)
        fun deleteTvShow(showID: Int, position: Int)
    }

    interface Model {
        fun searchTopRatedShows(page: Int): Observable<SearchTvShowResponse>
        fun searchTvOnTheAir(page: Int): Observable<SearchTvShowResponse>
        fun searchLatestShows(page: Int): Observable<SearchTvShowResponse>
        fun searchPopularShows(page: Int): Observable<SearchTvShowResponse>
        fun getFavoriteShows(page: Int): Observable<SearchTvShowResponse>
        fun getWatchlistShows(page: Int): Observable<SearchTvShowResponse>
        fun getRecommendedShows(showID: Int, page: Int): Observable<SearchTvShowResponse>
        fun deleteFromFavoriteTV(showID: Int): Observable<ResponseMessage>
        fun deleteFromWatchListTV(showID: Int): Observable<ResponseMessage>
    }
}