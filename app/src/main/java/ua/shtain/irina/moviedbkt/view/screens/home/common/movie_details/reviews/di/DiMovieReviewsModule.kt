package ua.shtain.irina.moviedbkt.view.screens.home.common.movie_details.reviews.di

import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import ua.shtain.irina.moviedbkt.domain.MovieRepository
import ua.shtain.irina.moviedbkt.root.network.RetrofitHelper
import ua.shtain.irina.moviedbkt.root.network.servises.MovieService
import ua.shtain.irina.moviedbkt.root.rx.SchedulerHelper
import ua.shtain.irina.moviedbkt.view.screens.home.common.movie_details.reviews.ReviewsContract
import ua.shtain.irina.moviedbkt.view.screens.home.common.movie_details.reviews.ReviewsPresenter
import ua.shtain.irina.moviedbkt.view.screens.home.di.MainScope

/**
 * Created by Alex Shtain on 20.03.2018.
 */
@Module
class DiMovieReviewsModule {
    @Provides
    @MainScope
    fun provideMovieReviewsPresenter(compositeDisposable: CompositeDisposable, repository: MovieRepository) = ReviewsPresenter(compositeDisposable, repository)

    @Provides
    @MainScope
    fun provideDataRepository(helper: RetrofitHelper, schedulerHelper: SchedulerHelper): ReviewsContract.Model = MovieRepository(helper.createService(MovieService::class.java), schedulerHelper)
}