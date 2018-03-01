package ua.shtain.irina.moviedbkt.model.star

import com.google.gson.annotations.SerializedName

/**
 * Created by Irina Shtain on 01.03.2018.
 */
data class StarDetails(@SerializedName("profile_path") val profilePath: String,
                       @SerializedName("name") val name: String,
                       @SerializedName("biography") val biography: String,
                       @SerializedName("birthday") val birthday: String?,
                       @SerializedName("deathday") val deathday: String?,
                       @SerializedName("place_of_birth") val placeOfBirth: String?,
                       @SerializedName("popularity") val popularity: Float,
                       @SerializedName("id") val id: Int
)