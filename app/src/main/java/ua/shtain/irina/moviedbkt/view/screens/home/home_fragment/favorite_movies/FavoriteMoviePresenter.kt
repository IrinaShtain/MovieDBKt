package ua.shtain.irina.moviedbkt.view.screens.home.home_fragment.favorite_movies

import io.reactivex.disposables.CompositeDisposable
import ua.shtain.irina.moviedbkt.view.screens.home.common.movies.MoviesContract
import ua.shtain.irina.moviedbkt.view.screens.home.common.movies.MoviesPresenter
import javax.inject.Inject

/**
 * Created by Irina Shtain on 22.02.2018.
 */
class FavoriteMoviePresenter@Inject constructor(compositeDisposable: CompositeDisposable,
                                                model: MoviesContract.Model) : MoviesPresenter() {
    init {
        mModel = model
        mCompositeDisposable = compositeDisposable
    }

    override fun getMovies(page: Int) = mModel.getFavoriteMovies(page)
}