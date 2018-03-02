package ua.shtain.irina.moviedbkt.model.tv

import com.google.gson.annotations.SerializedName
import ua.shtain.irina.moviedbkt.model.genre.GenreItem
import java.util.ArrayList

/**
 * Created by Irina Shtain on 01.03.2018.
 */
data class TvShowItem(@SerializedName("name") val title: String,
                      @SerializedName("first_air_date") val firstAirDate: String,
                      @SerializedName("overview") val overview: String,
                      @SerializedName("poster_path") val posterPath: String,
                      @SerializedName("genres") val genres: ArrayList<GenreItem>,
                      @SerializedName("id") val id: Int,
                      @SerializedName("vote_average") val voteAverage: Float
)