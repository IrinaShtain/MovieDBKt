package ua.shtain.irina.moviedbkt.view.screens.home.stars.stars_details.images.di

import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import ua.shtain.irina.moviedbkt.domain.StarRepository
import ua.shtain.irina.moviedbkt.view.screens.home.di.MainScope
import ua.shtain.irina.moviedbkt.view.screens.home.stars.stars_details.images.ImagesPresenter

/**
 * Created by Irina Shtain on 28.03.2018.
 */
@Module
class DiImagesModule {
    @Provides
    @MainScope
    fun provideSearchStarPresenter(compositeDisposable: CompositeDisposable, repository: StarRepository) = ImagesPresenter(compositeDisposable, repository)
}