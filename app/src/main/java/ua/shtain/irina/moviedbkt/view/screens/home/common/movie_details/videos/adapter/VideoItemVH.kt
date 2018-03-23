package ua.shtain.irina.moviedbkt.view.screens.home.common.movie_details.videos.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import ua.shtain.irina.moviedbkt.R

/**
 * Created by Irina Shtain on 22.03.2018.
 */
class VideoItemVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val tvName = itemView.findViewById(R.id.tvName) as TextView
    private val tvType = itemView.findViewById(R.id.tvType) as TextView
    private val tvSite = itemView.findViewById(R.id.tvSite) as TextView

    fun bindData(videoItemDH: VideoItemDH) {
        tvName.text = videoItemDH.name
        tvType.text = videoItemDH.type
        tvSite.text = videoItemDH.site
    }
}