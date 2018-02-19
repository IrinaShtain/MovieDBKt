package ua.shtain.irina.moviedbkt.model.movie

import com.google.gson.annotations.SerializedName
import java.util.ArrayList

/**
 * Created by Irina Shtain on 16.02.2018.
 */
data class MoviesResponse(@SerializedName("items") val movies: ArrayList<MovieItem>,
                          @SerializedName("name") val name: String,
                          @SerializedName("description") val description: String,
                          @SerializedName("id") val id: String,
                          @SerializedName("item_count") val itemCount: String

)