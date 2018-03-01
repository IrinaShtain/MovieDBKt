package ua.shtain.irina.moviedbkt.view.screens.home.stars.stars_details.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import ua.shtain.irina.moviedbkt.R

/**
 * Created by Irina Shtain on 01.03.2018.
 */
class FamousForVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private var icon = itemView.findViewById(R.id.imageView) as ImageView
    private var names = itemView.findViewById(R.id.tvTitle) as TextView
    private var info = itemView.findViewById(R.id.tv_info) as TextView
    private var release = itemView.findViewById(R.id.tv_release) as TextView

    fun bindData(famousForDH: FamousForDH) {
        names.text = famousForDH.title
        info.text = famousForDH.desc
        release.text = famousForDH.releaseDate
        Picasso.with(itemView.context)
                .load(famousForDH.posterPath)
                .error(R.drawable.placeholder_movie)
                .into(icon)
    }
}
