package ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.search.search_by_genre

import io.reactivex.disposables.CompositeDisposable
import ua.shtain.irina.moviedbkt.view.screens.home.common.movies.MoviesContract
import ua.shtain.irina.moviedbkt.view.screens.home.common.movies.MoviesPresenter
import javax.inject.Inject

/**
 * Created by Irina Shtain on 21.02.2018.
 */
class SearchMovieByGenrePresenter  @Inject constructor(compositeDisposable: CompositeDisposable,
                                                       model: MoviesContract.Model) : MoviesPresenter() {
    init {
        mModel = model
        mCompositeDisposable = compositeDisposable
    }

    override fun getMovies(page: Int) = mModel.searchMovieByGenre(genreId, page)
}