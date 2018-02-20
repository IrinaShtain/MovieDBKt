package ua.shtain.irina.moviedbkt.model.movie

import ua.shtain.irina.moviedbkt.other.Constants

/**
 * Created by Irina Shtain on 20.02.2018.
 */
fun MovieItem.getGenres(): String {
        val stringBuilder = StringBuilder()
        for (item in genres) {
            stringBuilder.append(item.name).append(" ")
        }
        return stringBuilder.toString().trim { it <= ' ' }
}

fun MovieItem.getAvatarUrl() = Constants.IMAGE_BASE + this.posterPath