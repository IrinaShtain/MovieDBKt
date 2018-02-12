package ua.shtain.irina.moviedbkt.root.rx

import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton

/**
 * Created by Irina Shtain on 12.02.2018.
 */

@Module
class DiRxSchedulerModule {
    @Provides
    @Singleton
    fun provideSchedulerUI(): Scheduler {
        return AndroidSchedulers.mainThread()
    }


    @Provides
    @Singleton
    fun provideSchedulerIO(): Scheduler {
        return Schedulers.io()
    }


    @Provides
    @Singleton
    fun provideSchedulerHelper(): SchedulerHelper {
        return SchedulerHelper(provideSchedulerUI(), provideSchedulerIO())
    }

}
