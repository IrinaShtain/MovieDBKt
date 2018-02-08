package ua.shtain.irina.moviedbkt.root

import android.app.Application
import com.facebook.stetho.Stetho

/**
 * Created by Irina Shtain on 30.01.2018.
 */
class MovieDBApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
        ObjectGraph.getInstance(this)
    }
}