package ua.shtain.irina.moviedbkt.view.screens.home.stars.stars_details.adapters

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import com.squareup.picasso.Picasso
import ua.shtain.irina.moviedbkt.R


/**
 * Created by Irina Shtain on 28.03.2018.
 */
class ScreenSlidePagerAdapter constructor(postImages: ArrayList<String>) : PagerAdapter() {

    val images = postImages

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as LinearLayout
    }


    override fun getCount() = images.size

    override fun destroyItem(container: ViewGroup, position: Int, item: Any) {
        container.removeView(item as LinearLayout)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = container.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.pager_image_item, null)

        val imageView = view.findViewById(R.id.imagePost) as ImageView
        Picasso.with(container.context)
                .load(images[position])
                .noFade()
                .placeholder(R.drawable.bg_title_black_gradient)
                .error(R.drawable.placeholder_star)
                .into(imageView)

        container.addView(view)

        return view
    }
}