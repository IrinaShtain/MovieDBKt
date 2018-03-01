package ua.shtain.irina.moviedbkt.model.star

import ua.shtain.irina.moviedbkt.other.Constants

/**
 * Created by Irina Shtain on 01.03.2018.
 */
fun StarItem.getAvatarUrl() = Constants.IMAGE_BASE + this.profilePath