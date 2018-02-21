package ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.search.di

import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import ua.shtain.irina.moviedbkt.domain.MovieRepository
import ua.shtain.irina.moviedbkt.root.network.RetrofitHelper
import ua.shtain.irina.moviedbkt.root.network.servises.MovieService
import ua.shtain.irina.moviedbkt.root.rx.SchedulerHelper
import ua.shtain.irina.moviedbkt.view.screens.home.di.MainScope
import ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.movies_in_list.MoviesInListContract
import ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.search.genre_adapter.GenreAdapter
import ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.search.latest_movies.SearchLatestMoviePresenter
import ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.search.popular_movies.SearchPopularMoviePresenter
import ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.search.search_by_genre.SearchMovieByGenrePresenter
import ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.search.search_by_title.SearchMovieByTitlePresenter

/**
 * Created by Irina Shtain on 21.02.2018.
 */
@Module
class DiSearchMoviesModule {
    @Provides
    @MainScope
    fun provideSearchPopularMoviePresenter(compositeDisposable: CompositeDisposable, repository: MovieRepository) = SearchPopularMoviePresenter(compositeDisposable, repository)

    @Provides
    @MainScope
    fun provideSearchLaterMoviePresenter(compositeDisposable: CompositeDisposable, repository: MovieRepository) = SearchLatestMoviePresenter(compositeDisposable, repository)

    @Provides
    @MainScope
    fun provideSearchMovieByGenrePresenter(compositeDisposable: CompositeDisposable, repository: MovieRepository) = SearchMovieByGenrePresenter(compositeDisposable, repository)

    @Provides
    @MainScope
    fun provideSearchMovieByTitlePresenter(compositeDisposable: CompositeDisposable, repository: MovieRepository) = SearchMovieByTitlePresenter(compositeDisposable, repository)

    @Provides
    @MainScope
    fun provideDataRepository(helper: RetrofitHelper, schedulerHelper: SchedulerHelper): MoviesInListContract.Model = MovieRepository(helper.createService(MovieService::class.java), schedulerHelper)

    @Provides
    @MainScope
    fun provideGenreAdapter() = GenreAdapter()
}