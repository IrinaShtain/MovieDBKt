package ua.shtain.irina.moviedbkt.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Irina Shtain on 09.02.2018.
 */
data class LoginSession( @SerializedName("session_id") val sessionID: String,
                         @SerializedName("success") val success: String)

