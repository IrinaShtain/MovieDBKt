package ua.shtain.irina.moviedbkt.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Irina Shtain on 09.02.2018.
 */
data class LoginToken(@SerializedName("request_token") val requestToken: String,
                        @SerializedName("success") val success: String)