package ua.shtain.irina.moviedbkt.model.star

import com.google.gson.annotations.SerializedName
import java.util.ArrayList

/**
 * Created by Irina Shtain on 19.02.2018.
 */
data class StarItem(@SerializedName("profile_path") val profilePath: String,
                    @SerializedName("name") val name: String,
                    @SerializedName("popularity") val popularity: Float,
                    @SerializedName("known_for") val knownFor: ArrayList<FamousForItem>,
                    @SerializedName("id") val id: Int
)