package ua.shtain.irina.moviedbkt.model.lists

import com.google.gson.annotations.SerializedName

/**
 * Created by Irina Shtain on 15.02.2018.
 */
 data class ListItem (@SerializedName("name") val name: String,
                      @SerializedName("list_type") val listType: String,
                      @SerializedName("description") val description: String,
                      @SerializedName("language") val language: String,
                      @SerializedName("poster_path") val poster_path: String,
                      @SerializedName("favorite_count") val favoriteCount: Int,
                      @SerializedName("item_count") val itemCount: Int,
                      @SerializedName("id") val id: Int)