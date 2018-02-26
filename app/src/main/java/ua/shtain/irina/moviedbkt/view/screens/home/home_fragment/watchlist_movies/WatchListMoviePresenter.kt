package ua.shtain.irina.moviedbkt.view.screens.home.home_fragment.watchlist_movies

import io.reactivex.disposables.CompositeDisposable
import ua.shtain.irina.moviedbkt.view.screens.home.common.movies.MoviesContract
import ua.shtain.irina.moviedbkt.view.screens.home.common.movies.MoviesPresenter
import javax.inject.Inject

/**
 * Created by Irina Shtain on 22.02.2018.
 */
class WatchListMoviePresenter @Inject constructor(compositeDisposable: CompositeDisposable,
                                                  model: MoviesContract.Model) : MoviesPresenter() {
    init {
        mModel = model
        mCompositeDisposable = compositeDisposable
    }

    override fun getMovies(page: Int) = mModel.getWatchlistMovies(page)
}