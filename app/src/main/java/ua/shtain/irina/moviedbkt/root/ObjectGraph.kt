package ua.shtain.irina.moviedbkt.root

import android.content.Context
import ua.shtain.irina.moviedbkt.view.screens.home.di.DaggerDiHomeComponent


import ua.shtain.irina.moviedbkt.view.screens.home.di.DiHomeComponent
import ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.add_list.di.DiCreateNewListModule
import ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.di.DiMovieListsModule
import ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.movies_in_list.di.DiMovieInListModule
import ua.shtain.irina.moviedbkt.view.screens.home.user_profile.di.DiUserProfileModule
import ua.shtain.irina.moviedbkt.view.screens.login.di.DaggerDiLoginComponent
import ua.shtain.irina.moviedbkt.view.screens.login.di.DiLoginComponent
import ua.shtain.irina.moviedbkt.view.screens.login.di.DiLoginModule
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
    private val mHomeComponent: DiHomeComponent

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
        mHomeComponent = DaggerDiHomeComponent
                .builder()
                .diRootComponent(mRootComponent)
                .diUserProfileModule(DiUserProfileModule())
                .diMovieListsModule(DiMovieListsModule())
                .diCreateNewListModule(DiCreateNewListModule())
                .diMovieInListModule(DiMovieInListModule())
                .build()

    }

    fun getSplashComponent(): DiSplashComponent {
        return mSplashComponent
    }

    fun getLoginComponent(): DiLoginComponent {
        return mLoginComponent
    }

    fun getHomeComponent(): DiHomeComponent {
        return mHomeComponent
    }


}