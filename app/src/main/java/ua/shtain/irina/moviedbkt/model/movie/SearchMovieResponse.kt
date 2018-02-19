package ua.shtain.irina.moviedbkt.model.movie

import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Created by Irina Shtain on 19.02.2018.
 */
data class SearchMovieResponse(@SerializedName("results") val movies: ArrayList<MovieItem>,
@SerializedName("total_pages") val totalPages: Int,
@SerializedName("total_results") val totalResults: Int,
@SerializedName("page") val page: Int

)