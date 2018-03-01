package ua.shtain.irina.moviedbkt.view.screens.home.stars.adapter

import ua.shtain.irina.moviedbkt.model.star.StarItem
import ua.shtain.irina.moviedbkt.model.star.getAvatarUrl

/**
 * Created by Irina Shtain on 01.03.2018.
 */
class StarDH(val model: StarItem) {

    val name: String
        get() = model.name.trim()

    val posterPath: String
        get() = model.getAvatarUrl()

    val id: Int
        get() = model.id

}
