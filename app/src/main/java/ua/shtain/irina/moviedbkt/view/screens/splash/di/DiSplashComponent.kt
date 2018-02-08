package ua.shtain.irina.moviedbkt.view.screens.splash.di

import dagger.Component
import ua.shtain.irina.moviedbkt.root.DiRootComponent
import ua.shtain.irina.moviedbkt.view.screens.splash.SplashActivity

/**
 * Created by Irina Shtain on 31.01.2018.
 */
@SplashScope
@Component(modules = arrayOf(DiSplashModule::class), dependencies = arrayOf(DiRootComponent::class))
interface DiSplashComponent {

   fun inject(activity: SplashActivity)

}