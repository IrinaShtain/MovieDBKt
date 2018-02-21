package ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.movies_in_list.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import ua.shtain.irina.moviedbkt.R
import ua.shtain.irina.moviedbkt.view.screens.common.OnCardClickListener
import java.util.ArrayList

/**
 * Created by Irina Shtain on 19.02.2018.
 */
class MovieItemAdapter : RecyclerView.Adapter<MovieItemVH>() {
    private var items: MutableList<MovieItemDH>? = null
    private var mListener: OnCardClickListener? = null

    init {
        items = ArrayList()
    }

    fun setListDH(listsDHs: MutableList<MovieItemDH>) {
        items = listsDHs
        notifyDataSetChanged()
    }

    fun addListDH(listsDHs: List<MovieItemDH>) {
        items!!.addAll(listsDHs)
        notifyDataSetChanged()
    }

    fun deleteItem(position: Int) {
        items!!.removeAt(position)
        notifyItemChanged(position)
    }

    fun setListener(listener: OnCardClickListener) {
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemVH {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.list_item, parent, false)
        return MovieItemVH(view)

    }

    override fun onBindViewHolder(holder: MovieItemVH, position: Int) {
        if (mListener != null) {
            holder.itemView.setOnClickListener({ v -> mListener!!.onCardClick(items!![position].getMovieID(), position) })
        }
        holder.bindData(items!![position])
    }

    override fun getItemCount(): Int {
        return if (items == null)
            0
        else {
            items!!.size
        }
    }

    fun clearData() {
        items?.clear()
        notifyDataSetChanged()
    }
}
