package ua.shtain.irina.moviedbkt.view.screens.home.common.movies

import android.util.Log
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import ua.shtain.irina.moviedbkt.model.exceptions.ConnectionException
import ua.shtain.irina.moviedbkt.model.movie.MovieItem
import ua.shtain.irina.moviedbkt.model.movie.SearchMovieResponse
import ua.shtain.irina.moviedbkt.model.genre.GenreItem
import ua.shtain.irina.moviedbkt.other.Constants
import ua.shtain.irina.moviedbkt.view.base.content.ContentView
import ua.shtain.irina.moviedbkt.view.screens.home.common.movies.adapter.MovieItemDH
import ua.shtain.irina.moviedbkt.view.screens.home.common.search_movies.genre_adapter.GenreDH

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
    protected var movieId: Int = 0

    private var mCurrentPage: Int = 0
    private var mTotalPages = Integer.MAX_VALUE
    private var genres: ArrayList<GenreDH> = ArrayList()

    private var mNeedRefresh = true
    private var totalResults: Int = 0

    abstract fun getMovies(page: Int): Observable<SearchMovieResponse>

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

                Constants.TYPE_RECOMMENDED_MOVIES ->{
                    movieId = mView.getMovieID()
                    loadMovies()
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

                Constants.TYPE_RECOMMENDED_MOVIES ->{
                    movieId = mView.getMovieID()
                    loadMovies()
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
                    totalResults = response.totalResults
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

    private fun prepareMovieList(items: ArrayList<MovieItem>): ArrayList<MovieItemDH> {
        val dhs = items.mapTo(ArrayList()) { MovieItemDH(it) }
        if (searchType == Constants.TYPE_FAVORITE_MOVIES || searchType == Constants.TYPE_WATCHLIST_MOVIES)
            for (dh in dhs) dh.isInList = true
        return dhs
    }

    override fun deletionConfirmed(itemId: Int, position: Int) {
        when (searchType) {
            Constants.TYPE_FAVORITE_MOVIES -> mCompositeDisposable.add(mModel.deleteFromFavoriteMovies(itemId)
                    .subscribe({ _ ->
                        mView.hideProgress()
                        mView.showMessage(Constants.MessageType.MOVIE_WAS_DELETED)
                        mView.updateMovies(position)
                        --totalResults
                        checkEmptyList()
                    }, { throwable ->
                        Log.e("myLog", "throwable " + throwable.localizedMessage)
                        mView.hideProgress()
                        when {
                            throwable.message.equals("HTTP 500 Internal Server Error") -> { // backend's bug :(
                                mView.showMessage(Constants.MessageType.TV_SHOW_WAS_DELETED)
                                mView.updateMovies(position)
                                --totalResults
                                checkEmptyList()
                            }
                            throwable is ConnectionException -> mView.showMessage(Constants.MessageType.CONNECTION_PROBLEMS)
                            else -> mView.showMessage(Constants.MessageType.UNKNOWN)
                        }
                    }))
            Constants.TYPE_WATCHLIST_MOVIES -> mCompositeDisposable.add(mModel.deleteFromWatchListMovies(itemId)
                    .subscribe({ _ ->
                        mView.hideProgress()
                        mView.showMessage(Constants.MessageType.TV_SHOW_WAS_DELETED)
                        mView.updateMovies(position)
                        --totalResults
                        checkEmptyList()
                    }, { throwable ->
                        Log.e("myLog", "throwable " + throwable.localizedMessage)
                        mView.hideProgress()
                        when {
                            throwable.message.equals("HTTP 500 Internal Server Error") -> { // backend's bug :(
                                mView.showMessage(Constants.MessageType.LIST_WAS_DELETED)
                                mView.updateMovies(position)
                                --totalResults
                                checkEmptyList()
                            }
                            throwable is ConnectionException -> mView.showMessage(Constants.MessageType.CONNECTION_PROBLEMS)
                            else -> mView.showMessage(Constants.MessageType.UNKNOWN)
                        }
                    }))
        }
    }

    private fun checkEmptyList() {
        if (totalResults == 0)
            mView.showPlaceholder(Constants.PlaceholderType.EMPTY)
    }


    override fun deleteMovie(movieID: Int, position: Int) {
        mView.showAlert(movieID, position)
    }
}