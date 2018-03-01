package ua.shtain.irina.moviedbkt.view.screens.home.stars.stars_details.di

import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import ua.shtain.irina.moviedbkt.domain.StarRepository
import ua.shtain.irina.moviedbkt.root.network.RetrofitHelper
import ua.shtain.irina.moviedbkt.root.network.servises.StarService
import ua.shtain.irina.moviedbkt.root.rx.SchedulerHelper
import ua.shtain.irina.moviedbkt.view.screens.home.di.MainScope
import ua.shtain.irina.moviedbkt.view.screens.home.stars.SearchStarContract
import ua.shtain.irina.moviedbkt.view.screens.home.stars.stars_details.StarsDetailsPresenter
import ua.shtain.irina.moviedbkt.view.screens.home.stars.stars_details.adapter.FamousForAdapter

/**
 * Created by Irina Shtain on 01.03.2018.
 */
@Module
class DiStarsDetailsModule {
    @Provides
    @MainScope
    fun provideStarsDetailsPresenter(compositeDisposable: CompositeDisposable, repository: StarRepository) = StarsDetailsPresenter(compositeDisposable, repository)

    @Provides
    @MainScope
    fun provideDataRepository(helper: RetrofitHelper, schedulerHelper: SchedulerHelper): SearchStarContract.Model = StarRepository(helper.createService(StarService::class.java), schedulerHelper)

    @Provides
    @MainScope
    fun provideFamousForsAdapter() = FamousForAdapter()
}