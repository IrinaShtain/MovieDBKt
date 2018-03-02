package ua.shtain.irina.moviedbkt.root.rx

import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton


/**
 * Created by Irina Shtain on 09.02.2018.
 */
@Module
class DiRxModule {
    @Provides
     fun provideCompositeSubscription(): CompositeDisposable {
        return CompositeDisposable()
    }

}
