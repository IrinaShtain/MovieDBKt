package ua.shtain.irina.moviedbkt.view.screens.home.stars.stars_details

import io.reactivex.Observable
import ua.shtain.irina.moviedbkt.model.star.StarDetails
import ua.shtain.irina.moviedbkt.view.base.IBasePresenter
import ua.shtain.irina.moviedbkt.view.base.content.ContentView

/**
 * Created by Irina Shtain on 01.03.2018.
 */
interface StarsDetailsContract {
    interface View : ContentView {
        fun setupFamousFor()
        fun setStarName(starName: String)
        fun setStarRating(starRating: String)
        fun setStarBiography(biography: String)
        fun setStarImage(imageUrl: String)
        fun getPersonID(): Int

    }

    interface Presenter : IBasePresenter<StarsDetailsContract.View> {
    }

    interface Model {
        fun getStarDetails(personID: Int): Observable<StarDetails>
    }
}