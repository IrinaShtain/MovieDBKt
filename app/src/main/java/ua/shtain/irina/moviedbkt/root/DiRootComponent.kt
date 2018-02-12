package ua.shtain.irina.moviedbkt.root

import android.content.Context
import dagger.Component
import io.reactivex.disposables.CompositeDisposable
import ua.shtain.irina.moviedbkt.root.network.DiNetworkModule
import ua.shtain.irina.moviedbkt.root.network.INetworkManager
import ua.shtain.irina.moviedbkt.root.network.RetrofitHelper
import ua.shtain.irina.moviedbkt.root.session.ISessionManager
import ua.shtain.irina.moviedbkt.root.session.di.DiSessionModule
import ua.shtain.irina.moviedbkt.root.rx.DiRxModule
import ua.shtain.irina.moviedbkt.root.rx.DiRxSchedulerModule
import ua.shtain.irina.moviedbkt.root.rx.SchedulerHelper
import javax.inject.Singleton

/**
 * Created by Irina Shtain on 31.01.2018.
 */

@Singleton
@Component(modules = [(DiAppModule::class),
    (DiSessionModule::class),
    (DiRxModule::class),
    (DiRxSchedulerModule::class),
    (DiNetworkModule::class)
])
interface DiRootComponent {
    fun context(): Context
    fun sharedPrefManager(): ISessionManager

    fun compositeDisposable(): CompositeDisposable
    fun schedulerHelper(): SchedulerHelper

    fun retrofitHelper(): RetrofitHelper
    fun networkManager(): INetworkManager
}