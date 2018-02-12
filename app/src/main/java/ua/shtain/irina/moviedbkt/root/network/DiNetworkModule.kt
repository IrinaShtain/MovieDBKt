package ua.shtain.irina.moviedbkt.root.network

import android.content.Context
import dagger.Module
import dagger.Provides
import ua.shtain.irina.moviedbkt.root.session.SessionManager
import javax.inject.Singleton

/**
 * Created by Irina Shtain on 09.02.2018.
 */
@Module
class DiNetworkModule {

    @Provides
    @Singleton
    fun provideRetrofitHelper(network: INetworkManager,
                              sessionManager: SessionManager)
            : RetrofitHelper = RetrofitHelper(network, sessionManager)


    @Provides
    @Singleton
    fun provideNetworkManager(context: Context): INetworkManager {
        return NetworkManagerImpl(context)
    }

}
