package ua.shtain.irina.moviedbkt.view.screens.home.user_profile.di

import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import ua.shtain.irina.moviedbkt.domain.ProfileRepository
import ua.shtain.irina.moviedbkt.root.network.RetrofitHelper
import ua.shtain.irina.moviedbkt.root.network.servises.ProfileService
import ua.shtain.irina.moviedbkt.root.rx.SchedulerHelper
import ua.shtain.irina.moviedbkt.root.session.SessionManager
import ua.shtain.irina.moviedbkt.view.screens.home.di.MainScope
import ua.shtain.irina.moviedbkt.view.screens.home.user_profile.UserProfileContract
import ua.shtain.irina.moviedbkt.view.screens.home.user_profile.UserProfilePresenter

/**
 * Created by Irina Shtain on 13.02.2018.
 */
@Module
class DiUserProfileModule {

    @Provides
    @MainScope
    fun provideUserProfilePresenter(sessionManager: SessionManager, compositeDisposable: CompositeDisposable, loginRepository: ProfileRepository)
            : UserProfileContract.Presenter = UserProfilePresenter(sessionManager, compositeDisposable, loginRepository)

    @Provides
    @MainScope
    fun provideDataRepository(helper: RetrofitHelper, schedulerHelper: SchedulerHelper): UserProfileContract.Model {
        return ProfileRepository(helper.createService(ProfileService::class.java), schedulerHelper)
    }

}