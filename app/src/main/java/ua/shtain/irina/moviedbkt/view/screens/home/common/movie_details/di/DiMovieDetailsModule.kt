package ua.shtain.irina.moviedbkt.view.screens.home.common.movie_details.di

import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import ua.shtain.irina.moviedbkt.domain.MovieRepository
import ua.shtain.irina.moviedbkt.view.screens.home.common.movie_details.MovieDetailsPresenter
import ua.shtain.irina.moviedbkt.view.screens.home.di.MainScope

/**
 * Created by Irina Shtain on 19.02.2018.
 */
@Module
class DiMovieDetailsModule {
    @Provides
    @MainScope
    fun provideMovieDetailsPresenter(compositeDisposable: CompositeDisposable, repository: MovieRepository) = MovieDetailsPresenter(compositeDisposable, repository)
}