package ua.shtain.irina.moviedbkt.view.screens.home.common.movies

import android.util.Log
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import ua.shtain.irina.moviedbkt.model.exceptions.ConnectionException
import ua.shtain.irina.moviedbkt.model.movie.MovieItem
import ua.shtain.irina.moviedbkt.model.movie.SearchMovieResponse
import ua.shtain.irina.moviedbkt.model.movie.genre.GenreItem
import ua.shtain.irina.moviedbkt.other.Constants
import ua.shtain.irina.moviedbkt.view.base.content.ContentView
import ua.shtain.irina.moviedbkt.view.screens.home.common.movies.adapter.MovieItemDH
import ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.search.genre_adapter.GenreDH

/**
 * Created by Irina Shtain on 20.02.2018.
 */
abstract class MoviesPresenter : MoviesContract.Presenter {

    lateinit var mView: MoviesContract.View
    protected lateinit var mCompositeDisposable: CompositeDisposable
    protected lateinit var mModel: MoviesContract.Model

    protected var searchType: Int = 0
    protected var movieTitle: String = ""
    protected var genreId: Int = 0

    private var mCurrentPage: Int = 0
    private var mTotalPages = Integer.MAX_VALUE
    private var genres: ArrayList<GenreDH> = ArrayList()

    private var mNeedRefresh = true

    override fun setView(view: ContentView) {
        mView = view as MoviesContract.View
    }

    override fun subscribe() {
        if (mNeedRefresh) {
            searchType = mView.getSearchType()
            when (searchType) {
                Constants.SEARCH_TYPE_LATEST_MOVIES,
                Constants.SEARCH_TYPE_POPULAR_MOVIES,
                Constants.TYPE_FAVORITE_MOVIES,
                Constants.TYPE_WATCHLIST_MOVIES -> {
                    loadMovies()
                }

                Constants.SEARCH_TYPE_MOVIES_BY_GENRE -> {
                    genreId = 0
                    mView.setupGenresList()
                    loadGenres()
                }

                Constants.SEARCH_TYPE_MOVIES_BY_TITLE -> {
                    mView.setupSearchByTitle()
                }
            }
        } else {
            mNeedRefresh = true
            when (searchType) {
                Constants.SEARCH_TYPE_MOVIES_BY_GENRE -> {
                    mView.setupGenresList()
                    loadGenres()
                }
                Constants.SEARCH_TYPE_MOVIES_BY_TITLE -> {
                    mView.setupSearchByTitle()
                }
            }
        }
    }

    override fun unsubscribe() {
        mCompositeDisposable.clear()
    }

    override fun onRefresh() {
        when {
            searchType == Constants.SEARCH_TYPE_MOVIES_BY_GENRE && genreId == 0 || searchType == Constants.SEARCH_TYPE_MOVIES_BY_TITLE && movieTitle.isEmpty() -> mView.hideProgress()
            else -> loadMovies()
        }
    }

    override fun updateNeedRefresh(needRefresh: Boolean) {
        mNeedRefresh = needRefresh
    }

    override fun onSearchClick(movieTitle: String) {
        if (!movieTitle.isEmpty()) {
            this.movieTitle = movieTitle
            loadMovies()
        } else
            mView.showMessage(Constants.MessageType.INPUT_MOVIE_TITLE)
    }

    override fun getNextPage() {
        Log.e("myLog", "mCurrentPage" + mCurrentPage)
        if (mCurrentPage < mTotalPages) {
            mView.showProgressPagination()
            loadPage(++mCurrentPage)
        }
    }

    override fun searchByGenre(id: Int) {
        this.genreId = id
        loadMovies()
    }

    private fun loadMovies() {
        mView.showProgressMain()
        mCurrentPage = 1
        mTotalPages = Integer.MAX_VALUE
        loadPage(mCurrentPage)
    }

    private fun loadGenres() {
        if (genres.isEmpty()) {
            mView.showProgressMain()
            mCompositeDisposable.add(mModel.getGenres()
                    .subscribe({ response ->
                        mView.hideProgress()
                        genres = prepareGenres(response.genres)
                        mView.setGenres(genres)
                    }, { throwable ->
                        mView.hideProgress()
                        throwable.printStackTrace()
                        Log.e("myLogs", throwable.localizedMessage)
                        if (!genres.isEmpty())
                            if (throwable is ConnectionException) {
                                mView.showMessage(Constants.MessageType.CONNECTION_PROBLEMS)
                            } else {
                                mView.showMessage(Constants.MessageType.UNKNOWN)
                            }
                        else if (throwable is ConnectionException) {
                            mView.showPlaceholder(Constants.PlaceholderType.NETWORK)
                        } else {
                            mView.showPlaceholder(Constants.PlaceholderType.UNKNOWN)
                        }
                    }))
        } else
            mView.setGenres(genres)

    }

    private fun prepareGenres(genreItems: ArrayList<GenreItem>): ArrayList<GenreDH> {
        return genreItems.mapTo(ArrayList()) { GenreDH(it) }
    }

    private fun loadPage(pageNumber: Int) {
        mCompositeDisposable.add(getMovies(pageNumber)
                .subscribe({ response ->
                    mView.hideProgress()
                    mCurrentPage = pageNumber
                    mTotalPages = response.totalPages
                    if (mCurrentPage == 1)
                        if (!response.movies.isEmpty())
                            mView.setList(prepareMovieList(response.movies))
                        else
                            mView.showPlaceholder(Constants.PlaceholderType.EMPTY)
                    else
                        mView.addList(prepareMovieList(response.movies))

                }) { throwable ->
                    mView.hideProgress()
                    throwable.printStackTrace()
                    when {
                        mTotalPages != Integer.MAX_VALUE -> when (throwable) {
                            is ConnectionException -> mView.showMessage(Constants.MessageType.CONNECTION_PROBLEMS)
                            else -> mView.showMessage(Constants.MessageType.UNKNOWN)
                        }
                        throwable is ConnectionException -> mView.showPlaceholder(Constants.PlaceholderType.NETWORK)
                        else -> mView.showPlaceholder(Constants.PlaceholderType.UNKNOWN)
                    }
                })
    }

    abstract fun getMovies(page: Int): Observable<SearchMovieResponse>

    private fun prepareMovieList(items: ArrayList<MovieItem>) = items.mapTo(ArrayList()) { MovieItemDH(it) }
}