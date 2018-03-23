package ua.shtain.irina.moviedbkt.view.screens.home.common.tv_shows.di

import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import ua.shtain.irina.moviedbkt.domain.TVRepository
import ua.shtain.irina.moviedbkt.root.network.RetrofitHelper
import ua.shtain.irina.moviedbkt.root.network.servises.TvService
import ua.shtain.irina.moviedbkt.root.rx.SchedulerHelper
import ua.shtain.irina.moviedbkt.view.screens.home.common.search_shows.latest_shows.SearchLatestTvShowsPresenter
import ua.shtain.irina.moviedbkt.view.screens.home.common.search_shows.on_air_shows.SearchOnAirTvShowsPresenter
import ua.shtain.irina.moviedbkt.view.screens.home.common.search_shows.popular_shows.SearchPopularTvShowsPresenter
import ua.shtain.irina.moviedbkt.view.screens.home.common.search_shows.top_rated_shows.SearchTopRatedTvShowsPresenter
import ua.shtain.irina.moviedbkt.view.screens.home.common.tv_show_details.recommendations.RecommendedTvShowsPresenter
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
    fun provideLatestTvShowsPresenter(compositeDisposable: CompositeDisposable, repository: TVRepository) = SearchLatestTvShowsPresenter(compositeDisposable, repository)

    @Provides
    @MainScope
    fun providePopularTvShowsPresenter(compositeDisposable: CompositeDisposable, repository: TVRepository) = SearchPopularTvShowsPresenter(compositeDisposable, repository)

    @Provides
    @MainScope
    fun provideOnAirTvShowsPresenter(compositeDisposable: CompositeDisposable, repository: TVRepository) = SearchOnAirTvShowsPresenter(compositeDisposable, repository)

    @Provides
    @MainScope
    fun provideTopRatedTvShowsPresenter(compositeDisposable: CompositeDisposable, repository: TVRepository) = SearchTopRatedTvShowsPresenter(compositeDisposable, repository)

    @Provides
    @MainScope
    fun provideRecommendedTvShowsPresenter(compositeDisposable: CompositeDisposable, repository: TVRepository) = RecommendedTvShowsPresenter(compositeDisposable, repository)

    @Provides
    @MainScope
    fun provideDataRepository(helper: RetrofitHelper, schedulerHelper: SchedulerHelper): TvShowsContract.Model = TVRepository(helper.createService(TvService::class.java), schedulerHelper)
}