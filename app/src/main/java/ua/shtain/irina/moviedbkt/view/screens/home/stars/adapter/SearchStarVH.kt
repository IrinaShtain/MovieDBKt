package ua.shtain.irina.moviedbkt.view.screens.home.stars.adapter

import android.support.v4.view.ViewCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import ua.shtain.irina.moviedbkt.R


/**
 * Created by Irina Shtain on 01.03.2018.
 */
class SearchStarVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val tvTitle = itemView.findViewById(R.id.tvTitle) as TextView
    val ivImage = itemView.findViewById(R.id.ivImage) as ImageView

    fun bindData(starDH: StarDH) {
        ViewCompat.setTransitionName(ivImage, starDH.name)
        Picasso.with(itemView.context)
                .load(starDH.posterPath)
                .error(R.drawable.placeholder_star)
                .placeholder(R.drawable.placeholder_star)
                .into(ivImage)
        tvTitle.text = starDH.name
    }
}
