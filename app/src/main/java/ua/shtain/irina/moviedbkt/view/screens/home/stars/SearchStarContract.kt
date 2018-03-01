package ua.shtain.irina.moviedbkt.view.screens.home.stars

import io.reactivex.Observable
import ua.shtain.irina.moviedbkt.model.star.StarResponse
import ua.shtain.irina.moviedbkt.view.base.refresheble_content.RefreshablePresenter
import ua.shtain.irina.moviedbkt.view.base.refresheble_content.RefreshableView
import ua.shtain.irina.moviedbkt.view.screens.home.stars.adapter.StarDH
import java.util.ArrayList

/**
 * Created by Irina Shtain on 01.03.2018.
 */
interface SearchStarContract {
      interface View : RefreshableView {
        fun setList(starDHs: ArrayList<StarDH>)
        fun addList(starDHs: ArrayList<StarDH>)
    }

      interface Presenter : RefreshablePresenter {
        fun onSearchClick(star: String)
        fun getNextPage()
    }

    interface Model {
        fun getStars(name: String, page: Int): Observable<StarResponse>
    }
}