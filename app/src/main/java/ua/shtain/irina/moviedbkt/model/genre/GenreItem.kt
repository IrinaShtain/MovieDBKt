package ua.shtain.irina.moviedbkt.model.genre

import com.google.gson.annotations.SerializedName

/**
 * Created by Irina Shtain on 19.02.2018.
 */
data class GenreItem(@SerializedName("id") val id: Int,
                     @SerializedName("name") val name: String)