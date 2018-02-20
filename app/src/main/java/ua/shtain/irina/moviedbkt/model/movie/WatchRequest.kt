package ua.shtain.irina.moviedbkt.model.movie

import com.google.gson.annotations.SerializedName

/**
 * Created by Irina Shtain on 20.02.2018.
 */
data class WatchRequest(@SerializedName("media_type") val media_type: String,
                   @SerializedName("media_id") val media_id: Int,
                   @SerializedName("watchlist") val watchlist: Boolean)