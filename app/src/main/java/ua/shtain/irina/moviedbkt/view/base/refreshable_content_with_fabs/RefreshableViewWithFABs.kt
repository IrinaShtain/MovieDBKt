package ua.shtain.irina.moviedbkt.view.base.refreshable_content_with_fabs

import ua.shtain.irina.moviedbkt.view.base.refresheble_content.RefreshableView

/**
 * Created by Irina Shtain on 27.02.2018.
 */
interface RefreshableViewWithFABs : RefreshableView {

    fun openSearchByTitleScreen(listID: Int)
    fun openSearchByGenreScreen(listID: Int)
    fun openLatestSearchScreen(listID: Int)
    fun openPopularSearchScreen(listID: Int)
    fun closeFabMenu()
    fun openFabMenu()
}
