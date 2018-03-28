package ua.shtain.irina.moviedbkt.view.screens.home.stars.stars_details.images

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.fragment_images.*

import kotlinx.android.synthetic.main.view_placeholder.*
import ua.shtain.irina.moviedbkt.R
import ua.shtain.irina.moviedbkt.other.Constants
import ua.shtain.irina.moviedbkt.view.base.IBasePresenter
import ua.shtain.irina.moviedbkt.view.base.content.ContentFragment
import ua.shtain.irina.moviedbkt.view.base.content.ContentView
import ua.shtain.irina.moviedbkt.view.screens.home.MainActivity
import ua.shtain.irina.moviedbkt.view.screens.home.stars.stars_details.adapters.ScreenSlidePagerAdapter
import javax.inject.Inject

/**
 * Created by Irina Shtain on 28.03.2018.
 */
class ImagesFragment : ContentFragment(), ImagesContract.View {

    @Inject
    lateinit var mPresenter: ImagesPresenter

    private var mID = 0
    private var mTitle = ""

    override fun getLayoutRes() = R.layout.fragment_images

    override fun getPresenter(): IBasePresenter<ContentView>? = mPresenter as IBasePresenter<ContentView>?

    override fun initGraph() {
        mActivity.mObjectGraph.getHomeComponent().inject(this)
    }

    companion object {
        private val PERSON_ID = "person_id"
        private val STAR_NAME = "movie_title"
        fun newInstance(personID: Int, title: String): ImagesFragment {
            val fragment = ImagesFragment()
            val bundle = Bundle()
            bundle.putInt(PERSON_ID, personID)
            bundle.putString(STAR_NAME, title)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mID = arguments.getInt(PERSON_ID)
        mTitle = arguments.getString(STAR_NAME)

        (mActivity as MainActivity).getToolbarMan()?.displayToolbar(false)
        toolbar.setNavigationOnClickListener({ mActivity.onBackPressed() })
        toolbar.title = mTitle

        mPresenter.mView = this
        mPresenter.subscribe()
    }

    override fun getID() = mID

    override fun setImages(images: ArrayList<String>) {
        val adapter = ScreenSlidePagerAdapter(images)
        vpImages.adapter = adapter
    }

    override fun showProgressMain() {
        super.showProgressMain()
        (mActivity as MainActivity).getToolbarMan()?.displayToolbar(true)
        (mActivity as MainActivity).getToolbarMan()?.setTitle(mTitle)
    }

    override fun hideProgress() {
        super.hideProgress()
        (mActivity as MainActivity).getToolbarMan()?.displayToolbar(false)
    }

    override fun showPlaceholder(placeholderType: Constants.PlaceholderType) {
        super.showPlaceholder(placeholderType)
        if (placeholderType === Constants.PlaceholderType.EMPTY) {
            (mActivity as MainActivity).getToolbarMan()?.displayToolbar(true)
            (mActivity as MainActivity).getToolbarMan()?.setTitle(mTitle)
            ivPlaceholderImage_VC.setImageResource(R.drawable.placeholder_empty)
            tvPlaceholderMessage_VC.setText(R.string.err_msg_no_images)
        }
    }
}