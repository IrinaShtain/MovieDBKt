package ua.shtain.irina.moviedbkt.view.screens.home.common.reviews.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import ua.shtain.irina.moviedbkt.R

/**
 * Created by Irina Shtain on 20.03.2018.
 */
class ReviewItemVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val tvContent = itemView.findViewById(R.id.tvContent) as TextView
    private val tvAuthor = itemView.findViewById(R.id.tvAuthor) as TextView

    fun bindData(reviewItemDH: ReviewItemDH) {
        tvContent.text = reviewItemDH.content
        tvAuthor.text = reviewItemDH.author
    }
}