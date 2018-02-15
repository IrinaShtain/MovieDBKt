package ua.shtain.irina.moviedbkt.view.screens.home.di

import dagger.Component
import ua.shtain.irina.moviedbkt.root.DiRootComponent
import ua.shtain.irina.moviedbkt.view.screens.home.MainActivity
import ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.MovieListsFragment
import ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.di.DiMovieListsModule
import ua.shtain.irina.moviedbkt.view.screens.home.user_profile.UserProfileFragment
import ua.shtain.irina.moviedbkt.view.screens.home.user_profile.di.DiUserProfileModule

/**
 * Created by Irina Shtain on 13.02.2018.
 */
@MainScope
@Component(modules = arrayOf(DiUserProfileModule::class,
        DiMovieListsModule::class),
        dependencies = arrayOf(DiRootComponent::class))
interface DiHomeComponent {

    fun inject(activity: MainActivity)
    fun inject(fragment: UserProfileFragment)
    fun inject(fragment: MovieListsFragment)
}
