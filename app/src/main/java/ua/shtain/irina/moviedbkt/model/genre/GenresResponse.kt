package ua.shtain.irina.moviedbkt.model.genre

import com.google.gson.annotations.SerializedName
import java.util.ArrayList

/**
 * Created by Irina Shtain on 19.02.2018.
 */
data class GenresResponse(@SerializedName("genres") val genres: ArrayList<GenreItem>)