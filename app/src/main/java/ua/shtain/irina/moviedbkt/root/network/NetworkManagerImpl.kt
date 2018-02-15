package ua.shtain.irina.moviedbkt.root.network

import android.content.Context
import android.net.ConnectivityManager

/**
 * Created by Irina Shtain on 09.02.2018.
 */
class NetworkManagerImpl
constructor(context: Context) : INetworkManager {

    private val mCm: ConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    override val isConnected: Boolean
        get() {
            val activeNetwork = mCm.activeNetworkInfo
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting
        }

}