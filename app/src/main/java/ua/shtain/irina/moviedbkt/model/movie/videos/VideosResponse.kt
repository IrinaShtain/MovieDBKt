package ua.shtain.irina.moviedbkt.model.movie.videos

import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Created by Irina Shtain on 22.03.2018.
 */
class VideosResponse(@SerializedName("results") val videos: ArrayList<VideosItem>)