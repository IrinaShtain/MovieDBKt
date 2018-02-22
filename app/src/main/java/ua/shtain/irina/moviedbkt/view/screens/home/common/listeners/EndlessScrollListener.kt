package ua.shtain.irina.moviedbkt.view.screens.home.common.listeners

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

/**
 * Created by Irina Shtain on 15.02.2018.
 */
class EndlessScrollListener(private val mLayoutManager: LinearLayoutManager, private val listener: OnNextPageListener) : RecyclerView.OnScrollListener() {

    private val visibleThreshold = 3
    private var previousTotalItemCount = 0
    private var loading = true
    private var totalItemCount: Int = 0
    private var lastVisibleItemPosition: Int = 0
    private var firstVisibleItem: Int = 0
    private val isReverseLayout: Boolean

    init {
        isReverseLayout = mLayoutManager.reverseLayout
    }

    // This happens many times a second during a scroll, so be wary of the code you place here.
    // We are given a few useful parameters to help us work out if we need to load some more url,
    // but first we check if we are waiting for the previous load to finish.
    override fun onScrolled(view: RecyclerView?, dx: Int, dy: Int) {
        firstVisibleItem = mLayoutManager.findLastCompletelyVisibleItemPosition()
        totalItemCount = mLayoutManager.itemCount
        lastVisibleItemPosition = mLayoutManager.findLastVisibleItemPosition()
        // If it’s still loading, we check to see if the dataset count has
        // changed, if so we conclude it has finished loading and update the current page
        // number and total item count.
        if (loading && totalItemCount > previousTotalItemCount) {
            loading = false
            previousTotalItemCount = totalItemCount
        }
        // If it isn’t currently loading, we check to see if we have breached
        // the visibleThreshold and need to reload more url.
        // If we do need to reload some more url, we execute onLoadMore to fetch the url.
        // threshold should reflect how many total columns there are too
        if (!loading && needLoading()) {
            loading = listener.onLoadMore()
        }

    }

    private fun needLoading(): Boolean {
        return if (isReverseLayout)
            totalItemCount - firstVisibleItem <= visibleThreshold
        else
            visibleThreshold + firstVisibleItem >= totalItemCount
    }

    fun reset() {
        previousTotalItemCount = 0
    }
}
