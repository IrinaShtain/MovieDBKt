package ua.shtain.irina.moviedbkt.view.screens.home.common.movie_details.recommendations

import io.reactivex.disposables.CompositeDisposable
import ua.shtain.irina.moviedbkt.view.screens.home.common.movies.MoviesContract
import ua.shtain.irina.moviedbkt.view.screens.home.common.movies.MoviesPresenter
import javax.inject.Inject

/**
 * Created by Irina Shtain on 21.03.2018.
 */
class RecommendedMoviesPresenter @Inject constructor(compositeDisposable: CompositeDisposable,
                                                     model: MoviesContract.Model) : MoviesPresenter() {
    init {
        mModel = model
        mCompositeDisposable = compositeDisposable
    }

    override fun getMovies(page: Int) = mModel.getRecommendedMovies(movieId, page)
}