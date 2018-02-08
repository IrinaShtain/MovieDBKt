package ua.shtain.irina.moviedbkt.root

import android.content.Context
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Irina Shtain on 31.01.2018.
 */

@Singleton
@Component(modules = arrayOf(DiAppModule::class))
interface DiRootComponent{
   fun context(): Context
}