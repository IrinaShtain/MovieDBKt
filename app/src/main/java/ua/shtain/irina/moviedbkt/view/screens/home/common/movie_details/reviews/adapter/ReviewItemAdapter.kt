package ua.shtain.irina.moviedbkt.view.screens.home.common.movie_details.reviews.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import ua.shtain.irina.moviedbkt.R
import java.util.*

/**
 * Created by Irina Shtain on 20.03.2018.
 */
class ReviewItemAdapter : RecyclerView.Adapter<ReviewItemVH>() {
    private var items: MutableList<ReviewItemDH>? = null

    init {
        items = ArrayList()
    }

    fun setListDH(listsDHs: MutableList<ReviewItemDH>) {
        items = listsDHs
        notifyDataSetChanged()
    }

    fun addListDH(listsDHs: List<ReviewItemDH>) {
        items!!.addAll(listsDHs)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewItemVH {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.list_item_review, parent, false)
        return ReviewItemVH(view)
    }

    override fun onBindViewHolder(holder: ReviewItemVH, position: Int) {
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
