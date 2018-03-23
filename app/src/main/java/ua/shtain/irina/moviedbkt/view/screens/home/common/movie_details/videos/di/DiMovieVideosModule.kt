package ua.shtain.irina.moviedbkt.view.screens.home.common.movie_details.videos.di

import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import ua.shtain.irina.moviedbkt.domain.MovieRepository
import ua.shtain.irina.moviedbkt.root.network.RetrofitHelper
import ua.shtain.irina.moviedbkt.root.network.servises.MovieService
import ua.shtain.irina.moviedbkt.root.rx.SchedulerHelper
import ua.shtain.irina.moviedbkt.view.screens.home.common.movie_details.videos.VideosContract
import ua.shtain.irina.moviedbkt.view.screens.home.common.movie_details.videos.VideosPresenter
import ua.shtain.irina.moviedbkt.view.screens.home.di.MainScope

/**
 * Created by Irina Shtain on 22.03.2018.
 */
@Module
class DiMovieVideosModule {
    @Provides
    @MainScope
    fun provideMovieVideosPresenter(compositeDisposable: CompositeDisposable, repository: MovieRepository) = VideosPresenter(compositeDisposable, repository)

    @Provides
    @MainScope
    fun provideDataRepository(helper: RetrofitHelper, schedulerHelper: SchedulerHelper): VideosContract.Model = MovieRepository(helper.createService(MovieService::class.java), schedulerHelper)
}