package ua.shtain.irina.moviedbkt.model.movie.review

import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Created by Alex Shtain on 20.03.2018.
 */
data class ReviewResponse(@SerializedName("results") val reviews: ArrayList<ReviewItem>,
                          @SerializedName("total_pages") val totalPages: Int,
                          @SerializedName("total_results") val totalResults: Int,
                          @SerializedName("page") val page: Int

)