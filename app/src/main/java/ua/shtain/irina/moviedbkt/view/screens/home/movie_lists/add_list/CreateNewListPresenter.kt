package ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.add_list

import android.util.Log
import io.reactivex.disposables.CompositeDisposable
import ua.shtain.irina.moviedbkt.model.exceptions.ConnectionException
import ua.shtain.irina.moviedbkt.other.Constants
import javax.inject.Inject

/**
 * Created by Irina Shtain on 16.02.2018.
 */
class CreateNewListPresenter @Inject constructor(compositeDisposable: CompositeDisposable,
                                                 model: CreateNewListContract.Model) : CreateNewListContract.Presenter {
    lateinit var mView: CreateNewListContract.View

    private var mCompositeDisposable = compositeDisposable
    private var mModel = model
    override fun setView(view: CreateNewListContract.View) {
        mView = view
    }

    override fun subscribe() {

    }

    override fun unsubscribe() {
        mCompositeDisposable.clear()
    }

    override fun createNewList(title: String, description: String) {
        Log.e("myLog", "createNewList ")
        if (title.isEmpty())
            mView.showTitleError()
        else {
            mView.hideError()
            mView.showProgress()
            mCompositeDisposable.add(mModel.createList(title, description)
                    .subscribe({ response ->
                        mView.clearInput()
                        mView.hideDialogProgress()
                        mView.updateTargetFragment(response.listId, title, description)
                    }, { throwable: Throwable ->
                        mView.hideDialogProgress()
                        if (throwable is ConnectionException) {
                            mView.updateTargetFragment(Constants.ERROR_CODE_CONNECTION_LOST, title, description)
                        } else {
                            mView.updateTargetFragment(Constants.ERROR_CODE_UNKNOWN, title, description)
                        }
                        Log.e("myLog", "throwable " + throwable.message)
                    }))

        }
    }
}
