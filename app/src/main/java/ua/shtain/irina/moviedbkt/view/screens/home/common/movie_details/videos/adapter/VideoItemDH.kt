package ua.shtain.irina.moviedbkt.view.screens.home.common.movie_details.videos.adapter

import ua.shtain.irina.moviedbkt.model.movie.videos.VideosItem

/**
 * Created by Irina Shtain on 22.03.2018.
 */
class VideoItemDH(val model: VideosItem) {
    val name: String
        get() = model.name

    val url: String
        get() = model.key

    val type: String
        get() = model.type

    val site: String
        get() = model.site
}