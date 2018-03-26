package ua.shtain.irina.moviedbkt.view.screens.home.common.tv_show_details.tv_show_reviews.di

import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import ua.shtain.irina.moviedbkt.domain.TVRepository
import ua.shtain.irina.moviedbkt.view.screens.home.common.tv_show_details.tv_show_reviews.TvShowReviewsPresenter
import ua.shtain.irina.moviedbkt.view.screens.home.di.MainScope

/**
 * Created by Irina Shtain on 26.03.2018.
 */
@Module
class DiTvShowReviewsModule {
    @Provides
    @MainScope
    fun provideTvShowReviewsPresenter(compositeDisposable: CompositeDisposable, repository: TVRepository) = TvShowReviewsPresenter(compositeDisposable, repository)

}