package ua.shtain.irina.moviedbkt.view.screens.home.stars.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import ua.shtain.irina.moviedbkt.R
import ua.shtain.irina.moviedbkt.view.screens.home.common.listeners.StarListener
import java.util.*

/**
 * Created by Irina Shtain on 01.03.2018.
 */
class SearchStarAdapter : RecyclerView.Adapter<SearchStarVH>() {
    private var items: MutableList<StarDH>? = null
    private var mListener: StarListener? = null

    init {
        items = ArrayList()
    }

    fun setListDH(listsDHs: MutableList<StarDH>) {
        items = listsDHs
        notifyDataSetChanged()
    }

    fun addListDH(listsDHs: List<StarDH>) {
        items!!.addAll(listsDHs)
        notifyDataSetChanged()
    }

    fun deleteItem(position: Int) {
        items!!.removeAt(position)
        notifyDataSetChanged()
    }

    fun setListener(listener: StarListener) {
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchStarVH {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.list_item_star, parent, false)
        return SearchStarVH(view)
    }

    override fun onBindViewHolder(holder: SearchStarVH, position: Int) {
        if (mListener != null) {
            holder.itemView.setOnClickListener({ mListener!!.onStarClick(holder.ivImage, items?.get(position)!!.model) })
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