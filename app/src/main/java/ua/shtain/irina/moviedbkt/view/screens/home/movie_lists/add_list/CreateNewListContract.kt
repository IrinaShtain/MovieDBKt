package ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.add_list

import io.reactivex.Observable
import ua.shtain.irina.moviedbkt.model.lists.ResponseMessage
import ua.shtain.irina.moviedbkt.view.base.IBasePresenter
import ua.shtain.irina.moviedbkt.view.base.IBaseView
import ua.shtain.irina.moviedbkt.view.base.content.ContentView
import ua.shtain.irina.moviedbkt.view.screens.home.user_profile.UserProfileContract

/**
 * Created by Irina Shtain on 16.02.2018.
 */
interface CreateNewListContract {
    interface View : IBaseView {
        fun clearInput()
        fun updateTargetFragment(resultCode: Int, title: String, description: String)
        fun showTitleError()
        fun hideError()
        fun showProgress()
        fun hideDialogProgress()
    }

    interface Presenter : IBasePresenter<CreateNewListContract.View> {
        fun createNewList(title: String, description: String)
    }

    interface Model {
        fun createList(listTitle: String, listDesc: String): Observable<ResponseMessage>
    }

}