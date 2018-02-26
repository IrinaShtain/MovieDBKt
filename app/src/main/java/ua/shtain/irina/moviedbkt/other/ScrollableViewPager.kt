package ua.shtain.irina.moviedbkt.other

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent

/**
 * Created by Irina Shtain on 22.02.2018.
 */
class ScrollableViewPager(context: Context, attrs: AttributeSet) : ViewPager(context, attrs) {

    private var mEnabled = false

    init {
        this.mEnabled = true
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return if (this.mEnabled) {
            super.onTouchEvent(event)
        } else false

    }

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        return if (this.mEnabled) {
            super.onInterceptTouchEvent(event)
        } else false

    }

    fun setPagingEnabled(enabled: Boolean) {
        this.mEnabled = enabled
    }
}
