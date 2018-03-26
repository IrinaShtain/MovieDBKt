package ua.shtain.irina.moviedbkt.view.screens.home.common.movie_details.movie_reviews.di

import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import ua.shtain.irina.moviedbkt.domain.MovieRepository
import ua.shtain.irina.moviedbkt.view.screens.home.common.movie_details.movie_reviews.MovieReviewsPresenter
import ua.shtain.irina.moviedbkt.view.screens.home.di.MainScope

/**
 * Created by Irina Shtain on 26.03.2018.
 */
@Module
class DiMovieReviewsModule {
    @Provides
    @MainScope
    fun provideMovieReviewsPresenter(compositeDisposable: CompositeDisposable, repository: MovieRepository) = MovieReviewsPresenter(compositeDisposable, repository)
}