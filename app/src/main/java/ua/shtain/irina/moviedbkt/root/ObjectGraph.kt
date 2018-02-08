package ua.shtain.irina.moviedbkt.root

import android.content.Context
import ua.shtain.irina.moviedbkt.view.screens.splash.SplashActivity
import ua.shtain.irina.moviedbkt.view.screens.splash.di.DaggerDiSplashComponent
import ua.shtain.irina.moviedbkt.view.screens.splash.di.DiSplashComponent
import ua.shtain.irina.moviedbkt.view.screens.splash.di.DiSplashModule

/**
 * Created by Irina Shtain on 31.01.2018.
 */
class ObjectGraph private constructor(context: Context) {
    private val  mSplashComponent: DiSplashComponent
    private val mRootComponent: DiRootComponent

    companion object {
        private var instance: ObjectGraph? = null
        fun getInstance(context: Context): ObjectGraph {
            if (instance == null)
                instance = ObjectGraph(context)
            return instance!!
        }
    }

    init {
        mRootComponent = DaggerDiRootComponent
                .builder()
                .diAppModule(DiAppModule(context))
                .build()
        mSplashComponent = DaggerDiSplashComponent
                .builder()
                .diRootComponent(mRootComponent)
                .diSplashModule(DiSplashModule())
                .build()

    }

    fun getSplashComponent(): DiSplashComponent {
        return mSplashComponent
    }


}