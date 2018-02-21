package ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.search.genre_adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import ua.shtain.irina.moviedbkt.R
import java.util.*

/**
 * Created by Irina Shtain on 20.02.2018.
 */
class GenreAdapter : RecyclerView.Adapter<GenreVH>() {
    private var items: List<GenreDH>? = null
    private var mListener: OnGenreClickListener? = null

    init {
        items = ArrayList()
    }

    fun setListDH(listsDHs: List<GenreDH>) {
        items = listsDHs
        notifyDataSetChanged()
    }

    fun setListener(listener: OnGenreClickListener) {
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreVH {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.list_item_genre, parent, false)
        return GenreVH(view)

    }

    override fun onBindViewHolder(holder: GenreVH, position: Int) {
        if (mListener != null) {
            holder.itemView.setOnClickListener { v -> mListener!!.onGenreClick(items!![position].getGenreID(), position) }
        }
        holder.bindData(items!![position])
    }

    override fun getItemCount(): Int {
        return if (items == null) 0 else items!!.size
    }
}
