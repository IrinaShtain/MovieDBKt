package ua.shtain.irina.moviedbkt.view.screens.home.stars.di

import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import ua.shtain.irina.moviedbkt.domain.StarRepository
import ua.shtain.irina.moviedbkt.root.network.RetrofitHelper
import ua.shtain.irina.moviedbkt.root.network.servises.StarService
import ua.shtain.irina.moviedbkt.root.rx.SchedulerHelper
import ua.shtain.irina.moviedbkt.view.screens.home.di.MainScope
import ua.shtain.irina.moviedbkt.view.screens.home.stars.SearchStarContract
import ua.shtain.irina.moviedbkt.view.screens.home.stars.SearchStarPresenter

/**
 * Created by Irina Shtain on 01.03.2018.
 */

@Module
class DiStarsModule {
    @Provides
    @MainScope
    fun provideSearchStarPresenter(compositeDisposable: CompositeDisposable, repository: StarRepository) = SearchStarPresenter(compositeDisposable, repository)

    @Provides
    @MainScope
    fun provideDataRepository(helper: RetrofitHelper, schedulerHelper: SchedulerHelper): SearchStarContract.Model = StarRepository(provideStarService(helper), schedulerHelper)

    @Provides
    @MainScope
    fun provideStarService(helper: RetrofitHelper) =
            helper.createService(StarService::class.java)
}