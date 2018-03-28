package ua.shtain.irina.moviedbkt.model.star

import com.google.gson.annotations.SerializedName

/**
 * Created by Irina Shtain on 28.03.2018.
 */
data class StarImageResponse(@SerializedName("profiles") val imagesProfile: ArrayList<StarImage>)