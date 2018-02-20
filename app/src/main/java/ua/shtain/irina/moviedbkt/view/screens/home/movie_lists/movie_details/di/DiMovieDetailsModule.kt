package ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.movie_details.di

import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import ua.shtain.irina.moviedbkt.domain.MovieRepository
import ua.shtain.irina.moviedbkt.root.network.RetrofitHelper
import ua.shtain.irina.moviedbkt.root.network.servises.MovieService
import ua.shtain.irina.moviedbkt.root.rx.SchedulerHelper
import ua.shtain.irina.moviedbkt.view.screens.home.di.MainScope
import ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.movie_details.MovieDetailsPresenter
import ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.movies_in_list.MoviesInListContract

/**
 * Created by Irina Shtain on 19.02.2018.
 */
@Module
class DiMovieDetailsModule {
    @Provides
    @MainScope
    fun provideMovieDetailsPresenter(compositeDisposable: CompositeDisposable, repository: MovieRepository) = MovieDetailsPresenter(compositeDisposable, repository)

    @Provides
    @MainScope
    fun provideDataRepository(helper: RetrofitHelper, schedulerHelper: SchedulerHelper): MoviesInListContract.Model = MovieRepository(helper.createService(MovieService::class.java), schedulerHelper)

}