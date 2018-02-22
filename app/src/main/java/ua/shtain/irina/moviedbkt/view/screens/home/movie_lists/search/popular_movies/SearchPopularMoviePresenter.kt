package ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.search.popular_movies

import io.reactivex.disposables.CompositeDisposable
import ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.search.SearchMovieContract
import ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.search.SearchMoviePresenter
import javax.inject.Inject

/**
 * Created by Irina Shtain on 21.02.2018.
 */
class SearchPopularMoviePresenter @Inject constructor(compositeDisposable: CompositeDisposable,
                                                      model: SearchMovieContract.Model) : SearchMoviePresenter() {
    init {
        mModel = model
        mCompositeDisposable = compositeDisposable
    }

    override fun getMovies(page: Int) = mModel.getPopularMovies(page)
}