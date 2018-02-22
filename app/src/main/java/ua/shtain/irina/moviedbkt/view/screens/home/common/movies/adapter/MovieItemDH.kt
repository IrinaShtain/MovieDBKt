package ua.shtain.irina.moviedbkt.view.screens.home.common.movies.adapter

import ua.shtain.irina.moviedbkt.model.movie.MovieItem
import ua.shtain.irina.moviedbkt.other.Constants

/**
 * Created by Irina Shtain on 16.02.2018.
 */
class MovieItemDH(val model: MovieItem) {
    var isInList = false

    fun getMovieTitle() = model.title.trim()

    fun getMovieID() = model.id

    fun getPosterPath() = Constants.IMAGE_BASE + model.posterPath

    fun makeVisible() {
        isInList = true
    }
}
