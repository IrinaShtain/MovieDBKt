package ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.movies_in_list.di

import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import ua.shtain.irina.moviedbkt.domain.MovieRepository
import ua.shtain.irina.moviedbkt.root.network.RetrofitHelper
import ua.shtain.irina.moviedbkt.root.network.servises.MovieService
import ua.shtain.irina.moviedbkt.root.rx.SchedulerHelper
import ua.shtain.irina.moviedbkt.view.screens.home.di.MainScope
import ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.movies_in_list.MoviesInListContract
import ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.movies_in_list.MoviesInListPresenter
import ua.shtain.irina.moviedbkt.view.screens.home.common.movies.adapter.MovieItemAdapter

/**
 * Created by Irina Shtain on 19.02.2018.
 */
@Module
class DiMovieInListModule {

    @Provides
    @MainScope
    fun provideMovieListsPresenter(compositeDisposable: CompositeDisposable, repository: MovieRepository) = MoviesInListPresenter(compositeDisposable, repository)

    @Provides
    @MainScope
    fun provideDataRepository(helper: RetrofitHelper, schedulerHelper: SchedulerHelper): MoviesInListContract.Model = MovieRepository(provideMovieService(helper), schedulerHelper)

    @Provides
    @MainScope
    fun provideMovieService(helper: RetrofitHelper) =
            helper.createService(MovieService::class.java)

    @Provides
    @MainScope
    fun provideMovieAdapter() = MovieItemAdapter()
}