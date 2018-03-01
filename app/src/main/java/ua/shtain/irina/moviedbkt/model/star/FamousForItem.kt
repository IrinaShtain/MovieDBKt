package ua.shtain.irina.moviedbkt.model.star

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * Created by Irina Shtain on 19.02.2018.
 */
data class FamousForItem(@SerializedName("poster_path") val posterPath: String,
                         @SerializedName("media_type") val mediaType: String,
                         @SerializedName("overview") val overview: String,
                         @SerializedName("title") val movieTitle: String,
                         @SerializedName("release_date") val releaseDate: String,
                         @SerializedName("name") val tvName: String,
                         @SerializedName("id") val id: Int,
                         @SerializedName("first_air_date") val firstAirDate: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(posterPath)
        parcel.writeString(mediaType)
        parcel.writeString(overview)
        parcel.writeString(movieTitle)
        parcel.writeString(releaseDate)
        parcel.writeString(tvName)
        parcel.writeInt(id)
        parcel.writeString(firstAirDate)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FamousForItem> {
        override fun createFromParcel(parcel: Parcel): FamousForItem {
            return FamousForItem(parcel)
        }

        override fun newArray(size: Int): Array<FamousForItem?> {
            return arrayOfNulls(size)
        }
    }
}
