package ua.shtain.irina.moviedbkt.root.network

import android.util.Log
import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ua.shtain.irina.moviedbkt.other.Constants
import ua.shtain.irina.moviedbkt.root.session.SessionManager
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by Irina Shtain on 09.02.2018.
 */
class RetrofitHelper @Inject constructor(network: INetworkManager,
                                         sessionManager: SessionManager) {
    val mNetwork = network
    val mSessionManager = sessionManager


    fun <T> createService(clas: Class<T>): T {

        val clientBuilder = OkHttpClient.Builder()
                .connectTimeout(Constants.TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(Constants.TIMEOUT_READ, TimeUnit.SECONDS)
                .writeTimeout(Constants.TIMEOUT_WRITE, TimeUnit.SECONDS)
                .addNetworkInterceptor(StethoInterceptor())
                .addInterceptor(QueryInterceptor(mSessionManager, mNetwork))
        Log.e("myLog", "Rest called ")
        val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(clientBuilder.build())
                .baseUrl(Constants.BASE_URL)
                .build()

        return retrofit.create(clas)
    }
}
