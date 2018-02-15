package ua.shtain.irina.moviedbkt.view.screens.home.movie_lists

import io.reactivex.Observable
import ua.shtain.irina.moviedbkt.model.lists.CreatedListsData
import ua.shtain.irina.moviedbkt.model.lists.ResponseMessage
import ua.shtain.irina.moviedbkt.view.base.refresheble_content.RefreshableView
import ua.shtain.irina.moviedbkt.view.base.refresheble_content.RefreshablePresenter
import ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.adapter.CreatedListsDH

/**
 * Created by Irina Shtain on 15.02.2018.
 */
interface MovieListsContract {
    interface View : RefreshableView {
        fun setLists(createdListsDHs: ArrayList<CreatedListsDH>)
        fun addLists(createdListsDHs: ArrayList<CreatedListsDH>)
        fun openListDetails(lisID: Int, listsName: String)
        fun deleteItem(pos: Int)
        fun addItem(createdListsDH: CreatedListsDH)
    }

    interface Presenter : RefreshablePresenter {
        fun getNextPage()
        fun loadPage(pageNumber: Int)
        fun showDetails(lisID: Int, item: CreatedListsDH)
        fun removeList(item: CreatedListsDH, pos: Int)
        fun showResult(resultID: Int, title: String, description: String)
    }

    interface Model {
        fun getLists(userID: Int, page: Int): Observable<CreatedListsData>
        fun deleteList(listID: Int): Observable<ResponseMessage>
    }
}