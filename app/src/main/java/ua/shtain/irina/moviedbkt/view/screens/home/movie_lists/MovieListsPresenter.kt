package ua.shtain.irina.moviedbkt.view.screens.home.movie_lists

import io.reactivex.disposables.CompositeDisposable
import ua.shtain.irina.moviedbkt.model.lists.ListItem
import ua.shtain.irina.moviedbkt.other.Constants
import ua.shtain.irina.moviedbkt.root.session.ISessionManager
import ua.shtain.irina.moviedbkt.view.base.content.ContentView
import ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.adapter.CreatedListsDH
import javax.inject.Inject

/**
 * Created by Irina Shtain on 15.02.2018.
 */
class MovieListsPresenter @Inject constructor(sessionManager: ISessionManager,
                                              compositeDisposable: CompositeDisposable,
                                              model: MovieListsContract.Model) : MovieListsContract.Presenter{
    lateinit var mView: MovieListsContract.View

    private var mSessionManager = sessionManager
    private var mCompositeDisposable = compositeDisposable
    private var mModel = model

    override fun setView(view: ContentView) {
        mView = view as MovieListsContract.View
    }

    override fun subscribe() {
       mView.showMessage(Constants.MessageType.CONNECTION_PROBLEMS)
        val list = (0..10).map { CreatedListsDH(ListItem(it.toString(), it.toString(), it.toString(), it.toString(), it.toString(), it, it, it)) }
        mView.setLists(list as ArrayList<CreatedListsDH>)
    }

    override fun showDetails(lisID: Int, item: CreatedListsDH) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun removeList(item: CreatedListsDH, pos: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun unsubscribe() {
        mCompositeDisposable.clear()
    }

    override fun onRefresh() {
    }

    override fun getNextPage() {

    }

    override fun loadPage(pageNumber: Int) {
    }

    override fun showResult(resultID: Int, title: String, description: String) {

    }

}