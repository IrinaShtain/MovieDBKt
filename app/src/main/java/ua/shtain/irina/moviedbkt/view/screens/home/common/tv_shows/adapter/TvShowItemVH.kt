package ua.shtain.irina.moviedbkt.view.screens.home.common.tv_shows.adapter

import android.support.v4.view.ViewCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import ua.shtain.irina.moviedbkt.R

/**
 * Created by Irina Shtain on 02.03.2018.
 */
class TvShowItemVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val tvTitle = itemView.findViewById(R.id.tvTitle) as TextView
    val ivImage = itemView.findViewById(R.id.ivImage) as ImageView
    val ivDelete = itemView.findViewById(R.id.ivDelete) as ImageView


    fun bindData(tvShowItemDH: TvShowItemDH) {
        ViewCompat.setTransitionName(ivImage, tvShowItemDH.title)
        Picasso.with(itemView.context)
                .load(tvShowItemDH.posterPath)
                .error(R.drawable.placeholder_movie)
                .placeholder(R.drawable.placeholder_movie)
                .into(ivImage)
        tvTitle.text = tvShowItemDH.title
        when {
            tvShowItemDH.isInList -> ivDelete.visibility = View.VISIBLE
            else -> ivDelete.visibility = View.GONE
        }
    }
}