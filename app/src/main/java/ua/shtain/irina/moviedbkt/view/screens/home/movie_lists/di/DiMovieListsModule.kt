package ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.di

import android.widget.ListAdapter
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import ua.shtain.irina.moviedbkt.domain.ProfileRepository
import ua.shtain.irina.moviedbkt.root.network.RetrofitHelper
import ua.shtain.irina.moviedbkt.root.network.servises.ProfileService
import ua.shtain.irina.moviedbkt.root.rx.SchedulerHelper
import ua.shtain.irina.moviedbkt.root.session.SessionManager
import ua.shtain.irina.moviedbkt.view.screens.home.di.MainScope
import ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.MovieListsContract
import ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.MovieListsPresenter
import ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.adapter.CreatedListsAdapter

/**
 * Created by Irina Shtain on 15.02.2018.
 */

@Module
class DiMovieListsModule {
    @Provides
    @MainScope
    fun provideMovieListsPresenter(sessionManager: SessionManager, compositeDisposable: CompositeDisposable, repository: ProfileRepository)
            = MovieListsPresenter(sessionManager, compositeDisposable, repository)

    @Provides
    @MainScope
    fun provideDataRepository(helper: RetrofitHelper, schedulerHelper: SchedulerHelper): MovieListsContract.Model
            = ProfileRepository(provideProfileService(helper), schedulerHelper)

    @Provides
    @MainScope
    fun provideProfileService(helper: RetrofitHelper) =
            helper.createService(ProfileService::class.java)

    @Provides
    @MainScope
    fun provideListAdapter() = CreatedListsAdapter()

}