package ua.shtain.irina.moviedbkt.view.screens.login.di

import dagger.Component
import ua.shtain.irina.moviedbkt.root.DiRootComponent
import ua.shtain.irina.moviedbkt.view.screens.login.LoginActivity
import ua.shtain.irina.moviedbkt.view.screens.login.content.LoginFragment

/**
 * Created by Irina Shtain on 09.02.2018.
 */
@LoginScope
@Component(modules = arrayOf(DiLoginModule::class), dependencies = arrayOf(DiRootComponent::class))
interface DiLoginComponent {

    fun inject(activity: LoginActivity)
    fun inject(fragment: LoginFragment)
}
