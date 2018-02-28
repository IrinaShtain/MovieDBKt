package ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.movies_in_list

import android.util.Log
import io.reactivex.disposables.CompositeDisposable
import ua.shtain.irina.moviedbkt.model.exceptions.ConnectionException
import ua.shtain.irina.moviedbkt.model.movie.MovieItem
import ua.shtain.irina.moviedbkt.other.Constants
import ua.shtain.irina.moviedbkt.view.base.content.ContentView
import ua.shtain.irina.moviedbkt.view.screens.home.common.movies.adapter.MovieItemDH
import javax.inject.Inject

/**
 * Created by Irina Shtain on 16.02.2018.
 */
class MoviesInListPresenter @Inject constructor(compositeDisposable: CompositeDisposable,
                                                model: MoviesInListContract.Model) : MoviesInListContract.Presenter {


    lateinit var mView: MoviesInListContract.View

    private var mCompositeDisposable = compositeDisposable
    private var mModel = model
    private var listID: Int = 0
    private var totalResults: Int = 0
    private var movieItems: ArrayList<MovieItem>? = null
    private var mIsFabOpen = false
    override fun setView(view: ContentView) {
        mView = view as MoviesInListContract.View
    }

    override fun subscribe() {
        listID = mView.getListID()
        mView.showProgressMain()
        loadMovies()

    }

    private fun loadMovies() {
        mCompositeDisposable.add(mModel.getMovies(listID)
                .subscribe({ moviesList ->
                    Log.e("myLog", "searchMoviesByTitle " + listID)
                    mView.hideProgress()
                    movieItems = moviesList.movies
                    if (!movieItems!!.isEmpty()) {
                        totalResults = movieItems!!.size
                        mView.setLists(prepareList(movieItems!!))
                    } else
                        mView.showPlaceholder(Constants.PlaceholderType.EMPTY)
                }, { throwable ->
                    mView.hideProgress()
                    if (movieItems != null)
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
                    Log.e("myLog", "throwable " + throwable.message)
                }))
    }

    private fun prepareList(items: ArrayList<MovieItem>): MutableList<MovieItemDH> {
        val result = items.mapTo(ArrayList()) { MovieItemDH(it) }
        for (item in result)
            item.isInList = true
        return result
    }


    override fun unsubscribe() {
        mCompositeDisposable.clear()
    }

    override fun onRefresh() {
        loadMovies()
    }

    override fun showDetails(movieID: Int) {
        mIsFabOpen = false
        mView.openMovieDetails(movieID)
    }

    override fun deleteMovieAlert(movieID: Int, position: Int) {
        mView.showConfirmAlert(movieID, position)
    }

    override fun deleteMovie(movieID: Int, position: Int) {
        Log.e("myLog", "deleteItem movieID = " + movieID)
        mCompositeDisposable.add(mModel.deleteMovie(listID, movieID)
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

    private fun checkEmptyList() {
        if (totalResults == 0)
            mView.showPlaceholder(Constants.PlaceholderType.EMPTY)
    }

//    override fun onMainFABClick() {
//        mIsFabOpen = if (mIsFabOpen) {
//            mView.closeFabMenu()
//            false
//        } else {
//            mView.openFabMenu()
//            true
//        }
//    }
//
//    override fun onFabFindUsingTitleClick() {
//        mIsFabOpen = false
//        mView.openSearchByTitleScreen(listID)
//    }
//
//    override fun onFabFindUsingGenreClick() {
//        mIsFabOpen = false
//        mView.openSearchByGenreScreen(listID)
//    }
//
//    override fun onFabFindPopularClick() {
//        mIsFabOpen = false
//        mView.openPopularSearchScreen(listID)
//    }
//
//    override fun onFabFindLatestClick() {
//        mIsFabOpen = false
//        mView.openLatestSearchScreen(listID)
//    }
}