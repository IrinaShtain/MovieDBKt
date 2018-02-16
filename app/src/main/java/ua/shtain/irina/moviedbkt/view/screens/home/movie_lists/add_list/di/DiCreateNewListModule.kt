package ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.add_list.di

import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import ua.shtain.irina.moviedbkt.domain.ProfileRepository
import ua.shtain.irina.moviedbkt.root.network.RetrofitHelper
import ua.shtain.irina.moviedbkt.root.network.servises.ProfileService
import ua.shtain.irina.moviedbkt.root.rx.SchedulerHelper
import ua.shtain.irina.moviedbkt.view.screens.home.di.MainScope
import ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.MovieListsContract
import ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.add_list.CreateNewListPresenter

/**
 * Created by Irina Shtain on 16.02.2018.
 */
@Module
class DiCreateNewListModule {
    @Provides
    @MainScope
    fun provideCreateNewListPresenter(compositeDisposable: CompositeDisposable, repository: ProfileRepository) = CreateNewListPresenter(compositeDisposable, repository)

    @Provides
    @MainScope
    fun provideDataRepository(helper: RetrofitHelper, schedulerHelper: SchedulerHelper): MovieListsContract.Model = ProfileRepository(helper.createService(ProfileService::class.java), schedulerHelper)

}