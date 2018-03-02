package ua.shtain.irina.moviedbkt.view.screens.home.common.tv_shows.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import ua.shtain.irina.moviedbkt.R
import ua.shtain.irina.moviedbkt.view.screens.home.common.listeners.OnCardClickListener
import ua.shtain.irina.moviedbkt.view.screens.home.common.listeners.OnDeleteClickListener
import java.util.*

/**
 * Created by Irina Shtain on 02.03.2018.
 */
class TvShowItemAdapter : RecyclerView.Adapter<TvShowItemVH>() {
    private var items: MutableList<TvShowItemDH>? = null
    private var mListener: OnCardClickListener? = null
    private var mDeleteListener: OnDeleteClickListener? = null

    init {
        items = ArrayList()
    }

    fun setListDH(listsDHs: MutableList<TvShowItemDH>) {
        items = listsDHs
        notifyDataSetChanged()
    }

    fun addListDH(listsDHs: List<TvShowItemDH>) {
        items!!.addAll(listsDHs)
        notifyDataSetChanged()
    }

    fun deleteItem(position: Int) {
        items!!.removeAt(position)
        notifyDataSetChanged()
    }

    fun setListener(listener: OnCardClickListener) {
        mListener = listener
    }

    fun setDeleteItemListener(listener: OnDeleteClickListener) {
        mDeleteListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowItemVH {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.list_item_movie, parent, false)
        return TvShowItemVH(view)
    }

    override fun onBindViewHolder(holder: TvShowItemVH, position: Int) {
        if (mListener != null) {
            holder.itemView.setOnClickListener({ mListener!!.onCardClick(items!![position].id, position) })
        }
        if (mDeleteListener != null) {
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
