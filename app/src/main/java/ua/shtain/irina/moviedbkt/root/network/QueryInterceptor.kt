package ua.shtain.irina.moviedbkt.root.network

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import ua.shtain.irina.moviedbkt.BuildConfig
import ua.shtain.irina.moviedbkt.model.exceptions.ConnectionException
import ua.shtain.irina.moviedbkt.model.exceptions.TimeoutException
import ua.shtain.irina.moviedbkt.root.session.SessionManager
import java.io.IOException
import java.net.SocketTimeoutException
import java.util.*

/**
 * Created by Irina Shtain on 12.02.2018.
 */
internal class QueryInterceptor(sessionManager: SessionManager, network: INetworkManager) : Interceptor {
    val mNetwork = network
    val mSessionManager = sessionManager

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        try {
            if (!mNetwork.isConnected) {
                throw ConnectionException()
            } else {
                var request = chain.request()
                val url = if (!mSessionManager.getSessionID().isEmpty())
                    request.url().newBuilder()
                            .addQueryParameter("api_key", BuildConfig.API_KEY)
                            .addQueryParameter("language", Locale.getDefault().toString())
                            .addQueryParameter("session_id", mSessionManager.getSessionID())
                            .build()
                else
                    request.url().newBuilder()
                            .addQueryParameter("api_key", BuildConfig.API_KEY)
                            .addQueryParameter("language", Locale.getDefault().toString())
                            .build()

                request = request.newBuilder().url(url).build()
                Log.e("myLog", "Rest called " + request.url())

                return chain.proceed(request)
            }
        } catch (e: SocketTimeoutException) {
            throw TimeoutException()
        }
    }

}
