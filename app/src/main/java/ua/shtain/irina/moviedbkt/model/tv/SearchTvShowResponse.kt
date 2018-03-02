package ua.shtain.irina.moviedbkt.model.tv

import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Created by Irina Shtain on 02.03.2018.
 */
data class SearchTvShowResponse (@SerializedName("results") val tvShows: ArrayList<TvShowItem>,
                                 @SerializedName("total_pages") val totalPages: Int,
                                 @SerializedName("total_results") val totalResults: Int,
                                 @SerializedName("page") val page: Int

)