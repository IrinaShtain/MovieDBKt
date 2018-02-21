package ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.search.genre_adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import ua.shtain.irina.moviedbkt.R

/**
 * Created by Irina Shtain on 20.02.2018.
 */
class GenreVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val tvGenreName: TextView = itemView.findViewById(R.id.tvGenreName) as TextView


    fun bindData(listsDH: GenreDH) {
        tvGenreName.text = listsDH.getGenreTitle()
    }
}