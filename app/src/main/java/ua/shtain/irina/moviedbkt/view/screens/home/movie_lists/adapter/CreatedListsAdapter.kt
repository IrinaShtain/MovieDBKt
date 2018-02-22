package ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import ua.shtain.irina.moviedbkt.R
import ua.shtain.irina.moviedbkt.view.screens.home.common.listeners.OnCardClickListener
import java.util.*
import javax.inject.Inject

/**
 * Created by Irina Shtain on 15.02.2018.
 */
class CreatedListsAdapter @Inject constructor() : RecyclerView.Adapter<CreatedListsVH>() {
    private var items: MutableList<CreatedListsDH>? = null
    private var mListener: OnCardClickListener? = null

    init {
        items = ArrayList()
    }

    fun setListDH(listsDHs: MutableList<CreatedListsDH>) {
        items = listsDHs
        notifyDataSetChanged()
    }

    fun addListDH(listsDHs: List<CreatedListsDH>) {
        items?.addAll(listsDHs)
        notifyDataSetChanged()
    }

    fun deleteItem(position: Int) {
        items?.removeAt(position)
        notifyDataSetChanged()
    }

    fun addItem(createdListsDH: CreatedListsDH) {
        items?.add(createdListsDH)
        notifyDataSetChanged()
    }

    fun getItem(position: Int): CreatedListsDH {
        return items!![position]
    }


    fun setListener(listener: OnCardClickListener) {
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreatedListsVH {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.list_item_created_list, parent, false)
        return CreatedListsVH(view)

    }

    override fun onBindViewHolder(holder: CreatedListsVH, position: Int) {
        if (mListener != null) {
            holder.itemView.setOnClickListener({ v -> mListener!!.onCardClick(items!![position].getListsID(), position) })
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
