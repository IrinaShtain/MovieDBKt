package ua.shtain.irina.moviedbkt.view.screens.home.common.videos.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import ua.shtain.irina.moviedbkt.R
import ua.shtain.irina.moviedbkt.view.screens.home.common.listeners.OnVideoClickListener
import java.util.*

/**
 * Created by Irina Shtain on 22.03.2018.
 */
class VideoAdapter : RecyclerView.Adapter<VideoItemVH>() {
    private var items: MutableList<VideoItemDH>? = null
    private var mListener: OnVideoClickListener? = null

    init {
        items = ArrayList()
    }

    fun setListDH(listsDHs: MutableList<VideoItemDH>) {
        items = listsDHs
        notifyDataSetChanged()
    }

    fun setListener(listener: OnVideoClickListener) {
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoItemVH {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.list_item_video, parent, false)
        return VideoItemVH(view)
    }

    override fun onBindViewHolder(holder: VideoItemVH, position: Int) {
        if (mListener != null) {
            holder.itemView.setOnClickListener({ mListener!!.onVideoClick(items!![position].url) })
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
