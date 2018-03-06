package ua.shtain.irina.moviedbkt.view.screens.home.stars.stars_details.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import ua.shtain.irina.moviedbkt.R
import ua.shtain.irina.moviedbkt.view.screens.home.common.listeners.OnItemFamousForClickListener
import java.util.*

/**
 * Created by Irina Shtain on 01.03.2018.
 */
class FamousForAdapter : RecyclerView.Adapter<FamousForVH>() {
    private var items: List<FamousForDH>? = null
    private var mListener: OnItemFamousForClickListener? = null

    init {
        items = ArrayList()
    }

    fun setListDH(listsDHs: List<FamousForDH>) {
        items = listsDHs
        notifyDataSetChanged()
    }

    fun setListener(listener: OnItemFamousForClickListener) {
        mListener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FamousForVH {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.list_item_famous_for, parent, false)
        return FamousForVH(view)

    }

    override fun onBindViewHolder(holder: FamousForVH, position: Int) {
        if (mListener != null) {
            holder.itemView.setOnClickListener({ mListener!!.onCardClick(position, holder.icon, items!![position].id, items!![position].title, items!![position].posterPath) })
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
