package ua.shtain.irina.moviedbkt.view.screens.home.common.movies.adapter

import ua.shtain.irina.moviedbkt.model.movie.MovieItem
import ua.shtain.irina.moviedbkt.other.Constants

/**
 * Created by Irina Shtain on 16.02.2018.
 */
class MovieItemDH(val model: MovieItem) {
    var isInList = false
    val title: String
        get() = model.title.trim()

    val id: Int
        get() = model.id

    val posterPath: String
        get() = Constants.IMAGE_BASE + model.posterPath
}
