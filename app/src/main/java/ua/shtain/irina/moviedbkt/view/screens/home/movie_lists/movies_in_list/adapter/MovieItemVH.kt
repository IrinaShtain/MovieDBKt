package ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.movies_in_list.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import ua.shtain.irina.moviedbkt.R

/**
 * Created by Irina Shtain on 19.02.2018.
 */
class MovieItemVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val tvTitle = itemView.findViewById(R.id.tvTitle) as TextView
    private val ivImage = itemView.findViewById(R.id.ivImage) as ImageView


    fun bindData(listsDH: MovieItemDH) {
        Picasso.with(itemView.context)
                .load(listsDH.getPosterPath())
                .error(R.drawable.placeholder_movie)
                .placeholder(R.drawable.placeholder_movie)
                .into(ivImage)
        tvTitle.text = listsDH.getMovieTitle()
    }
}