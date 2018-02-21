package ua.shtain.irina.moviedbkt.view.screens.home.di

import dagger.Component
import ua.shtain.irina.moviedbkt.root.DiRootComponent
import ua.shtain.irina.moviedbkt.view.screens.home.MainActivity
import ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.MovieListsFragment
import ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.add_list.CreateNewListDialog
import ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.add_list.di.DiCreateNewListModule
import ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.di.DiMovieListsModule
import ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.movie_details.MovieDetailsFragment
import ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.movie_details.MovieDetailsFragment_MembersInjector
import ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.movie_details.di.DiMovieDetailsModule
import ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.movies_in_list.MoviesInListFragment
import ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.movies_in_list.di.DiMovieInListModule
import ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.search.di.DiSearchMoviesModule
import ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.search.latest_movies.SearchLatestMovieFragment
import ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.search.popular_movies.SearchPopularMovieFragment
import ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.search.popular_movies.SearchPopularMoviePresenter
import ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.search.search_by_genre.SearchMovieByGenreFragment
import ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.search.search_by_title.SearchMovieByTitleFragment
import ua.shtain.irina.moviedbkt.view.screens.home.user_profile.UserProfileFragment
import ua.shtain.irina.moviedbkt.view.screens.home.user_profile.di.DiUserProfileModule

/**
 * Created by Irina Shtain on 13.02.2018.
 */
@MainScope
@Component(modules = arrayOf(DiUserProfileModule::class,
        DiMovieListsModule::class,
        DiCreateNewListModule::class,
        DiMovieInListModule::class,
        DiMovieDetailsModule::class,
        DiSearchMoviesModule::class
),
        dependencies = arrayOf(DiRootComponent::class))
interface DiHomeComponent {

    fun inject(activity: MainActivity)

    fun inject(fragment: UserProfileFragment)
    fun inject(fragment: MovieListsFragment)
    fun inject(fragment: CreateNewListDialog)
    fun inject(fragment: MoviesInListFragment)
    fun inject(fragment: MovieDetailsFragment)
    fun inject(fragment: SearchPopularMovieFragment)
    fun inject(fragment: SearchLatestMovieFragment)
    fun inject(fragment: SearchMovieByGenreFragment)
    fun inject(fragment: SearchMovieByTitleFragment)
}
