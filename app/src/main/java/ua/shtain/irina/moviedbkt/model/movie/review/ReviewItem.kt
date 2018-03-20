package ua.shtain.irina.moviedbkt.model.movie.review

import com.google.gson.annotations.SerializedName

/**
 * Created by Alex Shtain on 20.03.2018.
 */
data class ReviewItem (@SerializedName("author") val author: String,
                  @SerializedName("content") val content: String
)