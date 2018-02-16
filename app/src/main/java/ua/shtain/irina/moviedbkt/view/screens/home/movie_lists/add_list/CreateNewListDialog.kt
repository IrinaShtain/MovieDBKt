package ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.add_list

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.jakewharton.rxbinding2.view.RxView
import kotlinx.android.synthetic.main.fragment_create_new_list.*
import ua.shtain.irina.moviedbkt.R
import ua.shtain.irina.moviedbkt.other.Constants
import ua.shtain.irina.moviedbkt.view.base.BaseDialog
import ua.shtain.irina.moviedbkt.view.base.IBasePresenter
import ua.shtain.irina.moviedbkt.view.base.IBaseView
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by Irina Shtain on 16.02.2018.
 */
class CreateNewListDialog : BaseDialog(), CreateNewListContract.View {
    @Inject
    lateinit var mPresenter: CreateNewListPresenter

    override fun getPresenter() = mPresenter as IBasePresenter<IBaseView>

    override fun getLayoutRes() = R.layout.fragment_create_new_list

    override fun initGraph() {
        mActivity.mObjectGraph.getHomeComponent().inject(this)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPresenter.mView = this
        initUI()
    }

    protected fun initUI() {
        RxView.clicks(bt_addList)
                .throttleFirst(Constants.CLICK_DELAY, TimeUnit.MILLISECONDS)
                .subscribe { _ -> mPresenter.createNewList(titleWrapper.editText!!.text.toString(), descriptionWrapper.editText!!.text.toString()) }
        mPresenter.subscribe()
    }

    override fun clearInput() {
        text_title.setText("")
        text_description.setText("")
    }

    override fun updateTargetFragment(resultCode: Int, title: String, description: String) {
        val intent = Intent()
        intent.putExtra(Constants.KEY_TITLE, title)
        intent.putExtra(Constants.KEY_DESCRIPTION, description)
        intent.putExtra(Constants.KEY_ERROR_CODE, resultCode)
        targetFragment.onActivityResult(
                targetRequestCode, Activity.RESULT_OK, intent)
        dismiss()
    }

    override fun showTitleError() {
        titleWrapper.error = getString(R.string.error_empty_title)
    }

    override fun hideError() {
        titleWrapper.isErrorEnabled = false
        descriptionWrapper.isErrorEnabled = false
    }

    override fun showProgress() {
        pbPagination.visibility = View.VISIBLE
        hideKeyboard()
    }

    override fun hideDialogProgress() {
        pbPagination.visibility = View.GONE
    }


}