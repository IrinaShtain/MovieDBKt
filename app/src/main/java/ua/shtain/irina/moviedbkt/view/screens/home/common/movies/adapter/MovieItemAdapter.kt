package ua.shtain.irina.moviedbkt.view.screens.home.common.movies.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import ua.shtain.irina.moviedbkt.R
import ua.shtain.irina.moviedbkt.view.screens.home.common.listeners.OnCardClickListener
import ua.shtain.irina.moviedbkt.view.screens.home.common.listeners.OnDeleteClickListener
import ua.shtain.irina.moviedbkt.view.screens.home.common.listeners.OnItemClickListener
import java.util.ArrayList

/**
 * Created by Irina Shtain on 19.02.2018.
 */
class MovieItemAdapter : RecyclerView.Adapter<MovieItemVH>() {
    private var items: MutableList<MovieItemDH>? = null
    private var mListener: OnItemClickListener? = null
    private var mDeleteListener: OnDeleteClickListener? = null

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
        notifyDataSetChanged()
    }

    fun setListener(listener: OnItemClickListener) {
        mListener = listener
    }

    fun setDeleteItemListener(listener: OnDeleteClickListener) {
        mDeleteListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemVH {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.list_item_movie, parent, false)
        return MovieItemVH(view)
    }

    override fun onBindViewHolder(holder: MovieItemVH, position: Int) {
        if (mListener != null) {
            holder.itemView.setOnClickListener({ mListener!!.onCardClick(holder.ivImage, items!![position].id, items!![position].title, items!![position].posterPath) })
        }
        if (mDeleteListener!=null){
            holder.ivDelete.setOnClickListener({ mDeleteListener!!.onDeleteItemClick(items!![position].id, position) })
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

}
