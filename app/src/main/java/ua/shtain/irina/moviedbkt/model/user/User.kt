package ua.shtain.irina.moviedbkt.model.user

import com.google.gson.annotations.SerializedName

/**
 * Created by Irina Shtain on 13.02.2018.
 */
data class User(@SerializedName("name") val name: String,
                @SerializedName("username") val username: String,
                @SerializedName("iso_639_1") val language: String,
                @SerializedName("include_adult") val includeAdult: Boolean,
                @SerializedName("id") val id: Int)