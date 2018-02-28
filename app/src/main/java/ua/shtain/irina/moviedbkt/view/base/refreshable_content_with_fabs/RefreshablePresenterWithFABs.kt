package ua.shtain.irina.moviedbkt.view.base.refreshable_content_with_fabs

import ua.shtain.irina.moviedbkt.view.base.refresheble_content.RefreshablePresenter

/**
 * Created by Irina Shtain on 27.02.2018.
 */
interface RefreshablePresenterWithFABs : RefreshablePresenter {

    fun onMainFABClick()
    fun onFabFindUsingTitleClick()
    fun onFabFindUsingGenreClick()
    fun onFabFindPopularClick()
    fun onFabFindLatestClick()
}