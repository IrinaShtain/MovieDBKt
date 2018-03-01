package ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import ua.shtain.irina.moviedbkt.R

/**
 * Created by Irina Shtain on 15.02.2018.
 */
class CreatedListsVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val tvName: TextView = itemView.findViewById(R.id.tvName) as TextView
    private val tvDesc: TextView = itemView.findViewById(R.id.tv_description) as TextView
    private val tvType: TextView = itemView.findViewById(R.id.tv_type) as TextView


    fun bindData(listsDH: CreatedListsDH) {
        tvName.text = itemView.context.resources.getString(R.string.name, listsDH.listName)
        tvDesc.text = itemView.context.resources.getString(R.string.description, listsDH.description)
        tvType.text = itemView.context.resources.getString(R.string.type, listsDH.type)

    }

}