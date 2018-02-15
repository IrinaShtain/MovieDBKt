package ua.shtain.irina.moviedbkt.model.lists

import com.google.gson.annotations.SerializedName
import java.util.ArrayList

/**
 * Created by Irina Shtain on 15.02.2018.
 */
data class CreatedListsData(@SerializedName("results") val lists: ArrayList<ListItem>,
                            @SerializedName("total_pages") val totalPages: Int,
                            @SerializedName("total_results") val totalResults: Int,
                            @SerializedName("page") val page: Int) {
}