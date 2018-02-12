package ua.shtain.irina.moviedbkt.view.screens.login.di

import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import ua.shtain.irina.moviedbkt.domain.LoginRepository
import ua.shtain.irina.moviedbkt.root.network.RetrofitHelper
import ua.shtain.irina.moviedbkt.root.network.servises.LoginService
import ua.shtain.irina.moviedbkt.root.rx.SchedulerHelper
import ua.shtain.irina.moviedbkt.root.session.SessionManager
import ua.shtain.irina.moviedbkt.view.screens.login.content.LoginContract
import ua.shtain.irina.moviedbkt.view.screens.login.content.LoginPresenter

/**
 * Created by Irina Shtain on 09.02.2018.
 */
@Module
class DiLoginModule {

    @Provides
    @LoginScope
    fun provideSplashPresenter(sessionManager: SessionManager, compositeDisposable: CompositeDisposable, loginRepository: LoginRepository)
            : LoginContract.LoginPresenter = LoginPresenter(sessionManager, compositeDisposable, loginRepository)

    @Provides
    @LoginScope
    fun provideDataRepository(helper: RetrofitHelper, schedulerHelper: SchedulerHelper): LoginContract.LoginModel {
        return LoginRepository(helper.createService(LoginService::class.java), schedulerHelper)
    }

}