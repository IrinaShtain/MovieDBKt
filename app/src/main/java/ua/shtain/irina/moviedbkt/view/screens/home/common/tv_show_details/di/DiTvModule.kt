package ua.shtain.irina.moviedbkt.view.screens.home.common.tv_show_details.di

import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import ua.shtain.irina.moviedbkt.domain.TVRepository
import ua.shtain.irina.moviedbkt.root.network.RetrofitHelper
import ua.shtain.irina.moviedbkt.root.network.servises.TvService
import ua.shtain.irina.moviedbkt.root.rx.SchedulerHelper
import ua.shtain.irina.moviedbkt.view.screens.home.common.tv_show_details.TvDetailsPresenter
import ua.shtain.irina.moviedbkt.view.screens.home.di.MainScope

/**
 * Created by Irina Shtain on 01.03.2018.
 */
@Module
class DiTvModule {
    @Provides
    @MainScope
    fun provideTvDetailsPresenter(compositeDisposable: CompositeDisposable, repository: TVRepository) = TvDetailsPresenter(compositeDisposable, repository)

    @Provides
    @MainScope
    fun provideDataRepository(helper: RetrofitHelper, schedulerHelper: SchedulerHelper) = TVRepository(helper.createService(TvService::class.java), schedulerHelper)
}