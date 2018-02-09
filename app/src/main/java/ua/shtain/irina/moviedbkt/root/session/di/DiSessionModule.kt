package ua.shtain.irina.moviedbkt.root.session.di

import android.content.Context
import dagger.Module
import dagger.Provides
import ua.shtain.irina.moviedbkt.root.session.ISessionManager
import ua.shtain.irina.moviedbkt.root.session.SharedPrefManager
import javax.inject.Singleton

/**
 * Created by Irina Shtain on 09.02.2018.
 */

@Module
class DiSessionModule {

    @Provides
    @Singleton
   fun provideSharedPrefManager(context: Context): ISessionManager {
        return SharedPrefManager(context)
    }
}