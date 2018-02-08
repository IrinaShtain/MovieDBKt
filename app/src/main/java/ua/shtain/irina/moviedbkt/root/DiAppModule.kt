package ua.shtain.irina.moviedbkt.root

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Irina Shtain on 31.01.2018.
 */
@Module
class DiAppModule(private val mContext: Context) {

    @Provides
    @Singleton
    internal fun provideContext(): Context {
        return mContext
    }
}