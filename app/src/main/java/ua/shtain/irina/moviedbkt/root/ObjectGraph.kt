package ua.shtain.irina.moviedbkt.root

import android.content.Context
import ua.shtain.irina.moviedbkt.view.screens.login.di.DaggerDiLoginComponent
import ua.shtain.irina.moviedbkt.view.screens.login.di.DiLoginComponent
import ua.shtain.irina.moviedbkt.view.screens.login.di.DiLoginModule
import ua.shtain.irina.moviedbkt.view.screens.splash.SplashActivity
import ua.shtain.irina.moviedbkt.view.screens.splash.di.DaggerDiSplashComponent
import ua.shtain.irina.moviedbkt.view.screens.splash.di.DiSplashComponent
import ua.shtain.irina.moviedbkt.view.screens.splash.di.DiSplashModule

/**
 * Created by Irina Shtain on 31.01.2018.
 */
class ObjectGraph private constructor(context: Context) {
    private val  mSplashComponent: DiSplashComponent
    private val mRootComponent: DiRootComponent = DaggerDiRootComponent
            .builder()
            .diAppModule(DiAppModule(context))
            .build()
    private val  mLoginComponent : DiLoginComponent

    companion object {
        private var instance: ObjectGraph? = null
        fun getInstance(context: Context): ObjectGraph {
            if (instance == null)
                instance = ObjectGraph(context)
            return instance!!
        }
    }

    init {
        mSplashComponent = DaggerDiSplashComponent
                .builder()
                .diRootComponent(mRootComponent)
                .diSplashModule(DiSplashModule())
                .build()
        mLoginComponent = DaggerDiLoginComponent
                .builder()
                .diRootComponent(mRootComponent)
                .diLoginModule(DiLoginModule())
                .build()

    }

    fun getSplashComponent(): DiSplashComponent {
        return mSplashComponent
    }

    fun getLoginComponent(): DiLoginComponent {
        return mLoginComponent
    }


}