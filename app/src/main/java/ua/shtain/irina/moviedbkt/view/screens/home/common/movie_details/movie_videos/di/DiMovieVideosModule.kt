package ua.shtain.irina.moviedbkt.view.screens.home.common.movie_details.movie_videos.di

import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import ua.shtain.irina.moviedbkt.domain.MovieRepository
import ua.shtain.irina.moviedbkt.view.screens.home.common.movie_details.movie_videos.MovieVideosPresenter
import ua.shtain.irina.moviedbkt.view.screens.home.di.MainScope

/**
 * Created by Irina Shtain on 27.03.2018.
 */
@Module
class DiMovieVideosModule {
    @Provides
    @MainScope
    fun provideMovieVideosPresenter(compositeDisposable: CompositeDisposable, repository: MovieRepository) = MovieVideosPresenter(compositeDisposable, repository)
}