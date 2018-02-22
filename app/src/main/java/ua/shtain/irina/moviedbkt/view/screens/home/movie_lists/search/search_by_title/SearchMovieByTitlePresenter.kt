package ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.search.search_by_title

import io.reactivex.disposables.CompositeDisposable
import ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.search.MoviesContract
import ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.search.MoviesPresenter
import javax.inject.Inject

/**
 * Created by Irina Shtain on 21.02.2018.
 */
class SearchMovieByTitlePresenter @Inject constructor(compositeDisposable: CompositeDisposable,
                                                      model: MoviesContract.Model) : MoviesPresenter() {
    init {
        mModel = model
        mCompositeDisposable = compositeDisposable
    }

    override fun getMovies(page: Int) = mModel.searchMoviesByTitle(movieTitle, page)
}