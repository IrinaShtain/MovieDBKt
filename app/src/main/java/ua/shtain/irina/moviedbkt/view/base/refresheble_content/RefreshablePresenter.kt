package ua.shtain.irina.moviedbkt.view.base.refresheble_content

import ua.shtain.irina.moviedbkt.view.base.IBasePresenter
import ua.shtain.irina.moviedbkt.view.base.content.ContentView

/**
 * Created by Irina Shtain on 15.02.2018.
 */
interface RefreshablePresenter : IBasePresenter<ContentView> {
    fun onRefresh()
}