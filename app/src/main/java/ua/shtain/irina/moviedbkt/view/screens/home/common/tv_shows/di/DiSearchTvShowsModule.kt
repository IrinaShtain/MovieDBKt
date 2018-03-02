package ua.shtain.irina.moviedbkt.view.screens.home.common.tv_shows.di

import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import ua.shtain.irina.moviedbkt.domain.TVRepository
import ua.shtain.irina.moviedbkt.root.network.RetrofitHelper
import ua.shtain.irina.moviedbkt.root.network.servises.TvService
import ua.shtain.irina.moviedbkt.root.rx.SchedulerHelper
import ua.shtain.irina.moviedbkt.view.screens.home.common.tv_shows.TvShowsContract
import ua.shtain.irina.moviedbkt.view.screens.home.di.MainScope
import ua.shtain.irina.moviedbkt.view.screens.home.home_fragment.favorite_shows.FavoriteTvShowsPresenter
import ua.shtain.irina.moviedbkt.view.screens.home.home_fragment.watchlist_shows.WatchListTvShowsPresenter

/**
 * Created by Irina Shtain on 02.03.2018.
 */

@Module
class DiSearchTvShowsModule {
    @Provides
    @MainScope
    fun provideFavoriteShowsPresenter(compositeDisposable: CompositeDisposable, repository: TVRepository) = FavoriteTvShowsPresenter(compositeDisposable, repository)

    @Provides
    @MainScope
    fun provideWatchlistTvShowsPresenter(compositeDisposable: CompositeDisposable, repository: TVRepository) = WatchListTvShowsPresenter(compositeDisposable, repository)

    @Provides
    @MainScope
    fun provideDataRepository(helper: RetrofitHelper, schedulerHelper: SchedulerHelper): TvShowsContract.Model = TVRepository(helper.createService(TvService::class.java), schedulerHelper)
}