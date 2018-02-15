package ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import ua.shtain.irina.moviedbkt.R

/**
 * Created by Irina Shtain on 15.02.2018.
 */
class CreatedListsVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val tv_name: TextView
    private val tv_desc: TextView
    private val tv_type: TextView

    private val mContext: Context

    init {
        mContext = itemView.context
        tv_name = itemView.findViewById(R.id.tvName) as TextView
        tv_desc = itemView.findViewById(R.id.tv_description) as TextView
        tv_type = itemView.findViewById(R.id.tv_type) as TextView
    }

    fun bindData(listsDH: CreatedListsDH) {
        tv_name.text = mContext.resources.getString(R.string.name, listsDH.getListsName())
        tv_desc.text = mContext.resources.getString(R.string.description, listsDH.getListsDescription())
        tv_type.text = mContext.resources.getString(R.string.type, listsDH.getListsType())

    }

}