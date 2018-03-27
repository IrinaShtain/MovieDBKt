package ua.shtain.irina.moviedbkt.view.screens.home.common.tv_show_details.tv_show_videos.di

import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import ua.shtain.irina.moviedbkt.domain.TVRepository
import ua.shtain.irina.moviedbkt.view.screens.home.common.tv_show_details.tv_show_videos.TvShowVideosPresenter
import ua.shtain.irina.moviedbkt.view.screens.home.di.MainScope

/**
 * Created by Irina Shtain on 27.03.2018.
 */
@Module
class DiTvShowVideosModule {
    @Provides
    @MainScope
    fun provideMovieVideosPresenter(compositeDisposable: CompositeDisposable, repository: TVRepository) = TvShowVideosPresenter(compositeDisposable, repository)
}