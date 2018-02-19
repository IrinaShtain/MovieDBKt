package ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.movies_in_list.adapter

import ua.shtain.irina.moviedbkt.model.movie.MovieItem
import ua.shtain.irina.moviedbkt.other.Constants

/**
 * Created by Irina Shtain on 16.02.2018.
 */
class MovieItemDH(val model: MovieItem) {

    fun getMovieTitle() = if (model.title == null) "No title" else model.title.trim()

    fun getMovieID() = model.id

    fun getPosterPath() = Constants.IMAGE_BASE + model.posterPath
}
