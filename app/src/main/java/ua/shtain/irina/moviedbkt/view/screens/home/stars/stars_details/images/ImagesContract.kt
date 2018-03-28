package ua.shtain.irina.moviedbkt.view.screens.home.stars.stars_details.images

import io.reactivex.Observable
import ua.shtain.irina.moviedbkt.model.star.StarImageResponse
import ua.shtain.irina.moviedbkt.view.base.IBasePresenter
import ua.shtain.irina.moviedbkt.view.base.content.ContentView

/**
 * Created by Irina Shtain on 28.03.2018.
 */
interface ImagesContract {
    interface View : ContentView {
        fun getID(): Int
        fun setImages(images : ArrayList<String>)

    }

    interface Presenter : IBasePresenter<ImagesContract.View> {

    }

    interface Model {
        fun getStarImages(personID: Int): Observable<StarImageResponse>
    }
}