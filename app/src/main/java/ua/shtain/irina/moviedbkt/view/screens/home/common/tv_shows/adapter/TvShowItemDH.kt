package ua.shtain.irina.moviedbkt.view.screens.home.common.tv_shows.adapter

import ua.shtain.irina.moviedbkt.model.tv.TvShowItem
import ua.shtain.irina.moviedbkt.other.Constants

/**
 * Created by Irina Shtain on 02.03.2018.
 */
class TvShowItemDH(val model: TvShowItem) {
    var isInList = false
    val title: String
        get() = model.title.trim()

    val id: Int
        get() = model.id

    val posterPath: String
        get() = Constants.IMAGE_BASE + model.posterPath
}
