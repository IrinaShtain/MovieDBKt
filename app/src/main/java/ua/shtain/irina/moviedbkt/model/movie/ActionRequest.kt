package ua.shtain.irina.moviedbkt.model.movie

import com.google.gson.annotations.SerializedName

/**
 * Created by Irina Shtain on 19.02.2018.
 */
data class ActionRequest(@SerializedName("media_id") val mediaID: Int)