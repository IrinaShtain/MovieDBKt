package ua.shtain.irina.moviedbkt.view.screens.home.common.listeners

import android.widget.ImageView
import ua.shtain.irina.moviedbkt.model.star.StarItem

/**
 * Created by Irina Shtain on 01.03.2018.
 */
interface StarListener {
    fun onStarClick(imageView: ImageView, starItem: StarItem)
}