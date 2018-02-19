package ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.movies_in_list

import android.util.Log
import io.reactivex.disposables.CompositeDisposable
import ua.shtain.irina.moviedbkt.model.exceptions.ConnectionException
import ua.shtain.irina.moviedbkt.model.movie.MovieItem
import ua.shtain.irina.moviedbkt.other.Constants
import ua.shtain.irina.moviedbkt.view.base.content.ContentView
import ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.movies_in_list.adapter.MovieItemDH

/**
 * Created by Irina Shtain on 16.02.2018.
 */
class MoviesInListPresenter(compositeDisposable: CompositeDisposable,
                            model: MoviesInListContract.Model) : MoviesInListContract.Presenter {


    lateinit var mView: MoviesInListContract.View

    private var mCompositeDisposable = compositeDisposable
    private var mModel = model
    private var listID: Int = 0
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
                    Log.e("myLog", "getMoviesByTitle " + listID)
                    mView.hideProgress()
                    movieItems = moviesList.movies
                    if (!movieItems!!.isEmpty())
                        mView.setLists(prepareList(movieItems!!))
                    else
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

    private fun prepareList(items: ArrayList<MovieItem>) = items.mapTo(ArrayList()) { MovieItemDH(it) }


    override fun unsubscribe() {
        mCompositeDisposable.clear()
    }

    override fun onRefresh() {
        if (mIsFabOpen)
            mView.closeFabMenu()
        loadMovies()
    }

    override fun showDetails(movieID: Int) {
        mIsFabOpen = false
        mView.openMovieDetails(movieID, movieItems!!)
    }

    override fun deleteList(listID: Int) {
        Log.e("myLog", "deleteItem listID = " + listID)
        mCompositeDisposable.add(mModel.deleteList(listID)
                .subscribe({ _ ->
                    mView.hideProgress()
                    mView.showMessage(Constants.MessageType.LIST_WAS_DELETED)
                    mView.closeFragment()
                }, { throwable ->
                    Log.e("myLog", "throwable " + throwable.getLocalizedMessage())
                    mView.hideProgress()
                    when {
                        throwable.message.equals("HTTP 500 Internal Server Error") -> { // backend's bug :(
                            mView.showMessage(Constants.MessageType.LIST_WAS_DELETED)
                            mView.closeFragment()
                        }
                        throwable is ConnectionException -> mView.showMessage(Constants.MessageType.CONNECTION_PROBLEMS)
                        else -> mView.showMessage(Constants.MessageType.UNKNOWN)
                    }
                }))
    }

    override fun onMainFABClick() {
        if (mIsFabOpen) {
            mView.closeFabMenu()
            mIsFabOpen = false
        } else {
            mView.openFabMenu()
            mIsFabOpen = true
        }
    }

    override fun menuPressed() {
        mView.showAlert()
    }

    override fun onFabFindUsingTitleClick() {
        mIsFabOpen = false
        mView.openSearchByTitleScreen(listID, movieItems!!)
    }

    override fun onFabFindUsingGenreClick() {
        mIsFabOpen = false
        mView.openSearchByGenreScreen(listID, movieItems!!)
    }

    override fun onFabFindPopularClick() {
        mIsFabOpen = false
        mView.openPopularSearchScreen(listID, movieItems!!)
    }

    override fun onFabFindLatestClick() {
        mIsFabOpen = false
        mView.openLatestSearchScreen(listID, movieItems!!)
    }
}