package ua.shtain.irina.moviedbkt.view.screens.home.common.search_shows.top_rated_shows

import io.reactivex.disposables.CompositeDisposable
import ua.shtain.irina.moviedbkt.view.screens.home.common.tv_shows.TvShowsContract
import ua.shtain.irina.moviedbkt.view.screens.home.common.tv_shows.TvShowsPresenter
import javax.inject.Inject

/**
 * Created by Irina Shtain on 02.03.2018.
 */
class SearchTopRatedTvShowsPresenter @Inject constructor(compositeDisposable: CompositeDisposable,
                                                         model: TvShowsContract.Model) : TvShowsPresenter() {
    init {
        mModel = model
        mCompositeDisposable = compositeDisposable
    }

    override fun getShows(page: Int) = mModel.searchTopRatedShows(page)
}