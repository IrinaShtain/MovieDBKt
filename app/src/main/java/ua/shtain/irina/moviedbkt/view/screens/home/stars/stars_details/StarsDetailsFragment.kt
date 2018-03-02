package ua.shtain.irina.moviedbkt.view.screens.home.stars.stars_details

import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_star_details.*
import ua.shtain.irina.moviedbkt.R
import ua.shtain.irina.moviedbkt.model.star.*
import ua.shtain.irina.moviedbkt.view.base.IBasePresenter
import ua.shtain.irina.moviedbkt.view.base.content.ContentFragment
import ua.shtain.irina.moviedbkt.view.base.content.ContentView
import ua.shtain.irina.moviedbkt.view.screens.home.MainActivity
import ua.shtain.irina.moviedbkt.view.screens.home.common.listeners.OnCardClickListener
import ua.shtain.irina.moviedbkt.view.screens.home.common.movie_details.MovieDetailsFragment
import ua.shtain.irina.moviedbkt.view.screens.home.common.tv_show_details.TvShowDetailsFragment
import ua.shtain.irina.moviedbkt.view.screens.home.stars.stars_details.adapter.FamousForAdapter
import ua.shtain.irina.moviedbkt.view.screens.home.stars.stars_details.adapter.FamousForDH
import javax.inject.Inject

/**
 * Created by Irina Shtain on 01.03.2018.
 */
class StarsDetailsFragment : ContentFragment(), StarsDetailsContract.View, OnCardClickListener {
    @Inject
    lateinit var mPresenter: StarsDetailsPresenter
    @Inject
    lateinit var mAdapter: FamousForAdapter
    private var personID = 0
    private var famousForItem: ArrayList<FamousForItem> = ArrayList()

    override fun getLayoutRes() = R.layout.fragment_star_details

    override fun getPresenter() = mPresenter as IBasePresenter<ContentView>?

    override fun initGraph() {
        mActivity.mObjectGraph.getHomeComponent().inject(this)
    }

    companion object {
        private val PERSON_ID = "person_id"
        private val FAMOUS_FOR = "famous_for"
        fun newInstance(personID: Int, famousForItem: ArrayList<FamousForItem>): StarsDetailsFragment {
            val fragment = StarsDetailsFragment()
            val bundle = Bundle()
            bundle.putInt(PERSON_ID, personID)
            bundle.putParcelableArrayList(FAMOUS_FOR, famousForItem)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        personID = arguments.getInt(PERSON_ID)
        famousForItem = arguments.getParcelableArrayList(FAMOUS_FOR)
        initUI()
        mPresenter.mView = this
        mPresenter.subscribe()
    }

    private fun initUI() {
        toolbar.setNavigationOnClickListener({ mActivity.onBackPressed() })
        setupRecyclerView()
        setupCollapsingToolbar()
    }

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(mActivity, LinearLayoutManager.HORIZONTAL, false)
        rvListFamousFor.layoutManager = layoutManager
        rvListFamousFor.adapter = mAdapter
        mAdapter.setListener(this)
    }


    private fun setupCollapsingToolbar() {
        (mActivity as MainActivity).getToolbarMan()?.displayToolbar(false)
        appBarLayout.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {
            internal var scrollRange = -1
            internal var collapseOffset = -1

            override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.totalScrollRange
                    collapseOffset = appBarLayout.height / 3
                }
                if (scrollRange + verticalOffset <= collapseOffset) {
                    toolbar.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary))
                } else {
                    toolbar.setBackgroundResource(R.drawable.bg_title_black_gradient)
                    toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white)
                }
            }
        })
    }

    override fun getPersonID() = personID

    override fun setupFamousFor() {
        if (famousForItem.isEmpty())
            showNoFamousForMovies()
        else
            setFamousForDH(famousForItem.mapTo(ArrayList()) { FamousForDH(it) })
    }

    override fun setStarImage(imageUrl: String) {
        Picasso.with(context)
                .load(imageUrl)
                .error(R.drawable.placeholder_star)
                .into(imageView)
    }

    override fun setStarName(starName: String) {
        collapsingToolbar.title = starName
        collapsingToolbar.setCollapsedTitleTextColor(Color.WHITE)
    }

    override fun setStarRating(starRating: String) {
        tvPopularity.text = starRating
    }

    override fun setStarBiography(biography: String) {
        if (!biography.isEmpty())
            tvBiography.text = biography
        else
            showNoInformation()
    }

    private fun setFamousForDH(famousForDHs: List<FamousForDH>) {
        mAdapter.setListDH(famousForDHs)
    }

    private fun showNoFamousForMovies() {
        tvFamousFor.setText(R.string.msg_no_famous_for_movies)
    }

    private fun showNoInformation() {
        tvBiographyTitle.setText(R.string.msg_no_biography)
    }

    override fun onCardClick(itemID: Int, position: Int) {
        if (famousForItem[position].mediaType != "tv")
            mActivity.changeFragment(MovieDetailsFragment.newInstance(itemID, 0))
        else
            mActivity.changeFragment(TvShowDetailsFragment.newInstance(itemID))
    }

    override fun showProgressMain() {
        super.showProgressMain()
        (mActivity as MainActivity).getToolbarMan()?.displayToolbar(true)
    }

    override fun hideProgress() {
        super.hideProgress()
        (mActivity as MainActivity).getToolbarMan()?.displayToolbar(false)
    }

    override fun onDestroyView() {
        (mActivity as MainActivity).getToolbarMan()?.displayToolbar(true)
        super.onDestroyView()
    }

}