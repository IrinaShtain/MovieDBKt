package ua.shtain.irina.moviedbkt.model.movie

import com.google.gson.annotations.SerializedName
import ua.shtain.irina.moviedbkt.model.movie.genre.GenreItem
import java.util.ArrayList

/**
 * Created by Irina Shtain on 16.02.2018.
 */
data class MovieItem(@SerializedName("title") val title: String,
                     @SerializedName("release_date") val releaseDate: String,
                     @SerializedName("overview") val overview: String,
                     @SerializedName("poster_path") val posterPath: String,
                     @SerializedName("genres") val genres: ArrayList<GenreItem>,
                     @SerializedName("id") val id: Int,
                     @SerializedName("vote_average") val voteAverage: Float
                     )