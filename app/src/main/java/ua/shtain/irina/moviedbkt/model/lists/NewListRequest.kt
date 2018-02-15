package ua.shtain.irina.moviedbkt.model.lists

import com.google.gson.annotations.SerializedName

/**
 * Created by Irina Shtain on 15.02.2018.
 */
data class NewListRequest(@SerializedName("name") val name: String,
                          @SerializedName("description") val description: String)