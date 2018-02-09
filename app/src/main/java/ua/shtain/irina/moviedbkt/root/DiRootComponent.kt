package ua.shtain.irina.moviedbkt.root

import android.content.Context
import dagger.Component
import ua.shtain.irina.moviedbkt.root.session.ISessionManager
import ua.shtain.irina.moviedbkt.root.session.di.DiSessionModule
import javax.inject.Singleton

/**
 * Created by Irina Shtain on 31.01.2018.
 */

@Singleton
@Component(modules = arrayOf(DiAppModule::class, DiSessionModule::class))
interface DiRootComponent {
    fun context(): Context
    fun sharedPrefManager(): ISessionManager
}