package ua.shtain.irina.moviedbkt.model.lists

import com.google.gson.annotations.SerializedName

/**
 * Created by Irina Shtain on 15.02.2018.
 */
data class ResponseMessage(@SerializedName("status_code") val statusCode: Int,
                           @SerializedName("status_message") val statusMessage: String,
                           @SerializedName("list_id") val listId: Int)