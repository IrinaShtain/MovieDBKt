package ua.shtain.irina.moviedbkt.model.requests

import com.google.gson.annotations.SerializedName

/**
 * Created by Irina Shtain on 20.02.2018.
 */
data class FavoriteRequest(@SerializedName("media_type") val media_type: String,
                      @SerializedName("media_id") val media_id: Int,
                      @SerializedName("favorite") val isFavorite: Boolean)