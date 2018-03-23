package ua.shtain.irina.moviedbkt.model.movie.videos

import com.google.gson.annotations.SerializedName

/**
 * Created by Irina Shtain on 22.03.2018.
 */
data class VideosItem(@SerializedName("key") val key: String,
                      @SerializedName("name") val name: String,
                      @SerializedName("site") val site: String,
                      @SerializedName("type") val type: String
)