package ua.shtain.irina.moviedbkt.model.tv

import ua.shtain.irina.moviedbkt.other.Constants

/**
 * Created by Irina Shtain on 01.03.2018.
 */
fun TvShowItem.getPosterUrl() = Constants.IMAGE_BASE + this.posterPath

fun TvShowItem.getGenres(): String {
    val stringBuilder = StringBuilder()
    for (item in genres) {
        stringBuilder.append(item.name).append(" ")
    }
    return stringBuilder.toString().trim { it <= ' ' }
}
