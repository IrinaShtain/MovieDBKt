package ua.shtain.irina.moviedbkt.view.screens.home.common.tv_show_details.recommendations

import io.reactivex.disposables.CompositeDisposable
import ua.shtain.irina.moviedbkt.view.screens.home.common.tv_shows.TvShowsContract
import ua.shtain.irina.moviedbkt.view.screens.home.common.tv_shows.TvShowsPresenter
import javax.inject.Inject

/**
 * Created by Irina Shtain on 23.03.2018.
 */
class RecommendedTvShowsPresenter @Inject constructor(compositeDisposable: CompositeDisposable,
                                                      model: TvShowsContract.Model) : TvShowsPresenter() {
    init {
        mModel = model
        mCompositeDisposable = compositeDisposable
    }

    override fun getShows(page: Int) = mModel.getRecommendedShows(mTvShowID, page)
}