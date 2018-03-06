package ua.shtain.irina.moviedbkt.view.screens.home.common.listeners

import android.widget.ImageView

/**
 * Created by Irina Shtain on 06.03.2018.
 */
interface OnItemClickListener {
    fun onCardClick(imageView: ImageView, itemID: Int, title: String, posterUrl: String)
}