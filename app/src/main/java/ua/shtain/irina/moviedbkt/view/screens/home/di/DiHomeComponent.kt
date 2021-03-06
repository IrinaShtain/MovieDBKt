package ua.shtain.irina.moviedbkt.view.screens.home.di

import dagger.Component
import ua.shtain.irina.moviedbkt.root.DiRootComponent
import ua.shtain.irina.moviedbkt.view.screens.home.MainActivity
import ua.shtain.irina.moviedbkt.view.screens.home.common.movie_details.MovieDetailsFragment
import ua.shtain.irina.moviedbkt.view.screens.home.common.movie_details.di.DiMovieDetailsModule
import ua.shtain.irina.moviedbkt.view.screens.home.common.movie_details.movie_reviews.MovieReviewsFragment
import ua.shtain.irina.moviedbkt.view.screens.home.common.movie_details.movie_reviews.di.DiMovieReviewsModule
import ua.shtain.irina.moviedbkt.view.screens.home.common.movie_details.movie_videos.MovieVideosFragment
import ua.shtain.irina.moviedbkt.view.screens.home.common.movie_details.movie_videos.di.DiMovieVideosModule
import ua.shtain.irina.moviedbkt.view.screens.home.common.movie_details.recommendations.RecommendedMoviesFragment
import ua.shtain.irina.moviedbkt.view.screens.home.common.movies.di.DiSearchMoviesModule
import ua.shtain.irina.moviedbkt.view.screens.home.common.rating_dialog.RatingDialogFragment
import ua.shtain.irina.moviedbkt.view.screens.home.common.rating_dialog.di.DiRatingMovieModule
import ua.shtain.irina.moviedbkt.view.screens.home.common.search_movies.latest_movies.SearchLatestMovieFragment
import ua.shtain.irina.moviedbkt.view.screens.home.common.search_movies.popular_movies.SearchPopularMovieFragment
import ua.shtain.irina.moviedbkt.view.screens.home.common.search_movies.search_by_genre.SearchMovieByGenreFragment
import ua.shtain.irina.moviedbkt.view.screens.home.common.search_movies.search_by_title.SearchMovieByTitleFragment
import ua.shtain.irina.moviedbkt.view.screens.home.common.search_shows.latest_shows.SearchLatestTvShowsFragment
import ua.shtain.irina.moviedbkt.view.screens.home.common.search_shows.on_air_shows.SearchOnAirTvShowsFragment
import ua.shtain.irina.moviedbkt.view.screens.home.common.search_shows.popular_shows.SearchPopularTvShowsFragment
import ua.shtain.irina.moviedbkt.view.screens.home.common.search_shows.top_rated_shows.SearchTopRatedTvShowsFragment
import ua.shtain.irina.moviedbkt.view.screens.home.common.tv_show_details.TvShowDetailsFragment
import ua.shtain.irina.moviedbkt.view.screens.home.common.tv_show_details.di.DiTvModule
import ua.shtain.irina.moviedbkt.view.screens.home.common.tv_show_details.recommendations.RecommendedTvShowsFragment
import ua.shtain.irina.moviedbkt.view.screens.home.common.tv_show_details.tv_show_reviews.TvShowReviewsFragment
import ua.shtain.irina.moviedbkt.view.screens.home.common.tv_show_details.tv_show_reviews.di.DiTvShowReviewsModule
import ua.shtain.irina.moviedbkt.view.screens.home.common.tv_show_details.tv_show_videos.TvShowVideosFragment
import ua.shtain.irina.moviedbkt.view.screens.home.common.tv_show_details.tv_show_videos.di.DiTvShowVideosModule
import ua.shtain.irina.moviedbkt.view.screens.home.common.tv_shows.di.DiSearchTvShowsModule
import ua.shtain.irina.moviedbkt.view.screens.home.home_fragment.favorite_movies.FavoriteMovieFragment
import ua.shtain.irina.moviedbkt.view.screens.home.home_fragment.favorite_shows.FavoriteTvShowsFragment
import ua.shtain.irina.moviedbkt.view.screens.home.home_fragment.user_profile.UserProfileFragment
import ua.shtain.irina.moviedbkt.view.screens.home.home_fragment.user_profile.di.DiUserProfileModule
import ua.shtain.irina.moviedbkt.view.screens.home.home_fragment.watchlist_movies.WatchListMovieFragment
import ua.shtain.irina.moviedbkt.view.screens.home.home_fragment.watchlist_shows.WatchListTvShowsFragment
import ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.MovieListsFragment
import ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.add_list.CreateNewListDialog
import ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.add_list.di.DiCreateNewListModule
import ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.di.DiMovieListsModule
import ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.movies_in_list.MoviesInListFragment
import ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.movies_in_list.di.DiMovieInListModule
import ua.shtain.irina.moviedbkt.view.screens.home.settings.SettingsFragment
import ua.shtain.irina.moviedbkt.view.screens.home.settings.di.DiSettingsModule
import ua.shtain.irina.moviedbkt.view.screens.home.stars.SearchStarFragment
import ua.shtain.irina.moviedbkt.view.screens.home.stars.di.DiStarsModule
import ua.shtain.irina.moviedbkt.view.screens.home.stars.stars_details.StarsDetailsFragment
import ua.shtain.irina.moviedbkt.view.screens.home.stars.stars_details.di.DiStarsDetailsModule
import ua.shtain.irina.moviedbkt.view.screens.home.stars.stars_details.images.ImagesFragment
import ua.shtain.irina.moviedbkt.view.screens.home.stars.stars_details.images.di.DiImagesModule

/**
 * Created by Irina Shtain on 13.02.2018.
 */
@MainScope
@Component(modules = arrayOf(DiUserProfileModule::class,
        DiMovieListsModule::class,
        DiCreateNewListModule::class,
        DiMovieInListModule::class,
        DiMovieDetailsModule::class,
        DiSearchMoviesModule::class,
        DiRatingMovieModule::class,
        DiStarsModule::class,
        DiStarsDetailsModule::class,
        DiSettingsModule::class,
        DiTvModule::class,
        DiSearchTvShowsModule::class,
        DiMovieReviewsModule::class,
        DiTvShowReviewsModule::class,
        DiMovieVideosModule::class,
        DiTvShowVideosModule::class,
        DiImagesModule::class
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
    fun inject(fragment: FavoriteMovieFragment)
    fun inject(fragment: WatchListMovieFragment)
    fun inject(fragment: RatingDialogFragment)
    fun inject(fragment: SearchStarFragment)
    fun inject(fragment: StarsDetailsFragment)
    fun inject(fragment: TvShowDetailsFragment)
    fun inject(fragment: FavoriteTvShowsFragment)
    fun inject(fragment: WatchListTvShowsFragment)
    fun inject(fragment: SearchLatestTvShowsFragment)
    fun inject(fragment: SearchPopularTvShowsFragment)
    fun inject(fragment: SearchTopRatedTvShowsFragment)
    fun inject(fragment: SearchOnAirTvShowsFragment)
    fun inject(fragment: MovieReviewsFragment)
    fun inject(fragment: TvShowReviewsFragment)
    fun inject(fragment: RecommendedMoviesFragment)
    fun inject(fragment: MovieVideosFragment)
    fun inject(fragment: TvShowVideosFragment)
    fun inject(fragment: RecommendedTvShowsFragment)
    fun inject(fragment: SettingsFragment)
    fun inject(fragment: ImagesFragment)
}
