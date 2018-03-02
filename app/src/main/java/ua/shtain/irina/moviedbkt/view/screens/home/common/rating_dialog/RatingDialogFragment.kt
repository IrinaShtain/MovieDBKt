package ua.shtain.irina.moviedbkt.view.screens.home.common.rating_dialog

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import com.jakewharton.rxbinding2.view.RxView
import kotlinx.android.synthetic.main.fragment_dialog_rating.*
import ua.shtain.irina.moviedbkt.R
import ua.shtain.irina.moviedbkt.other.Constants
import ua.shtain.irina.moviedbkt.view.base.BaseDialog
import ua.shtain.irina.moviedbkt.view.base.IBasePresenter
import ua.shtain.irina.moviedbkt.view.base.IBaseView
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by Irina Shtain on 28.02.2018.
 */
class RatingDialogFragment : BaseDialog(), RatingDialogContract.View {
    @Inject
    lateinit var mPresenter: RatingDialogPresenter
    private var itemID: Int = 0
    private var mediaType: String = ""

    companion object {
        private val MOVIE_ID = "movie_id"
        private val MEDIA_TYPE = "media_type"
        fun newInstance(movieID: Int, mediaType: String): RatingDialogFragment {
            val fragment = RatingDialogFragment()
            val bundle = Bundle()
            bundle.putInt(MOVIE_ID, movieID)
            bundle.putString(MEDIA_TYPE, mediaType)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun getLayoutRes() = R.layout.fragment_dialog_rating

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemID = arguments.getInt(MOVIE_ID, 0)
        mediaType = arguments.getString(MEDIA_TYPE, "")
        mPresenter.mView = this
        mPresenter.subscribe()
        initUI()
    }

    private fun initUI() {
        RxView.clicks(btnRate)
                .throttleFirst(Constants.CLICK_DELAY, TimeUnit.MILLISECONDS)
                .subscribe { _ -> mPresenter.sendRating(rbMovieRating.rating) }

        RxView.clicks(btnCancel)
                .throttleFirst(Constants.CLICK_DELAY, TimeUnit.MILLISECONDS)
                .subscribe { _ -> dismiss() }
        tvTitle.text = resources.getString(R.string.title_rating, "")
        rbMovieRating.setOnRatingBarChangeListener({ _, rating, _ ->
            tvTitle.text = resources.getString(R.string.title_rating, rating.toString())
            hideRatingError()
        })
    }

    override fun onResume() {
        val params = dialog.window!!.attributes
        params.width = WindowManager.LayoutParams.MATCH_PARENT
        params.height = WindowManager.LayoutParams.WRAP_CONTENT
        dialog.window!!.attributes = params as android.view.WindowManager.LayoutParams
        super.onResume()
    }

    override fun updateTargetFragment(resultCode: Int) {
        val intent = Intent()
        intent.putExtra(Constants.KEY_ERROR_CODE, resultCode)
        targetFragment.onActivityResult(
                targetRequestCode, Activity.RESULT_OK, intent)
        dismiss()
    }

    override fun initGraph() {
        mActivity.mObjectGraph.getHomeComponent().inject(this)
    }

    override fun showProgress() {
        pbPagination.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        pbPagination.visibility = View.GONE
    }

    override fun getPresenter() = mPresenter as IBasePresenter<IBaseView>
    override fun showRatingError() {
        tvError.visibility = View.VISIBLE
    }

    override fun hideRatingError() {
        tvError.visibility = View.GONE
    }

    override fun getItemID() = itemID

    override fun getMediaType() = mediaType
}