package ua.shtain.irina.moviedbkt.model.star

import com.google.gson.annotations.SerializedName

/**
 * Created by Irina Shtain on 19.02.2018.
 */
data class FamousForItem (@SerializedName("poster_path") val posterPath: String,
                     @SerializedName("media_type") val mediaType: String,
                     @SerializedName("overview") val overview: String,
                     @SerializedName("movie_title") val movieTitle: String,
                     @SerializedName("release_date") val releaseDate: String,
                     @SerializedName("tv_name") val tv_Name: String,
                     @SerializedName("first_air_date") val firstAirDate: String
)