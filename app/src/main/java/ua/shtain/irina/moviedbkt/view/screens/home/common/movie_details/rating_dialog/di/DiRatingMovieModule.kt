package ua.shtain.irina.moviedbkt.view.screens.home.common.movie_details.rating_dialog.di

import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import ua.shtain.irina.moviedbkt.domain.MovieRepository
import ua.shtain.irina.moviedbkt.root.network.RetrofitHelper
import ua.shtain.irina.moviedbkt.root.network.servises.MovieService
import ua.shtain.irina.moviedbkt.root.rx.SchedulerHelper
import ua.shtain.irina.moviedbkt.view.screens.home.common.movie_details.rating_dialog.RatingDialogPresenter
import ua.shtain.irina.moviedbkt.view.screens.home.di.MainScope

/**
 * Created by Irina Shtain on 28.02.2018.
 */
@Module
class DiRatingMovieModule {
    @Provides
    @MainScope
    fun provideRatingMoviePresenter(compositeDisposable: CompositeDisposable, repository: MovieRepository) = RatingDialogPresenter(compositeDisposable, repository)

    @Provides
    @MainScope
    fun provideDataRepository(helper: RetrofitHelper, schedulerHelper: SchedulerHelper) = MovieRepository(helper.createService(MovieService::class.java), schedulerHelper)
}
