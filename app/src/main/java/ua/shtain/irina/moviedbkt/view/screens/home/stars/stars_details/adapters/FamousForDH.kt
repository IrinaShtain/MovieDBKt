package ua.shtain.irina.moviedbkt.view.screens.home.stars.stars_details.adapters

import ua.shtain.irina.moviedbkt.model.star.FamousForItem
import ua.shtain.irina.moviedbkt.model.star.getPosterUrl

/**
 * Created by Irina Shtain on 01.03.2018.
 */
class FamousForDH(val model: FamousForItem) {
    val id: Int
        get() = model.id

    val title: String
        get() = if (model.mediaType == "tv") model.tvName else model.movieTitle

    val releaseDate: String
        get() = if (model.mediaType == "tv") model.firstAirDate else model.releaseDate

    val posterPath: String
        get() = model.getPosterUrl()

    val desc: String
        get() = model.overview
}