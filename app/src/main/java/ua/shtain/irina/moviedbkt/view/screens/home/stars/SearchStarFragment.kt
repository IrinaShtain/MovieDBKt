package ua.shtain.irina.moviedbkt.view.screens.home.stars

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.jakewharton.rxbinding2.view.RxView
import kotlinx.android.synthetic.main.fragment_search_star.*
import ua.shtain.irina.moviedbkt.R
import ua.shtain.irina.moviedbkt.model.star.StarItem
import ua.shtain.irina.moviedbkt.other.Constants
import ua.shtain.irina.moviedbkt.view.base.refresheble_content.RefreshableFragment
import ua.shtain.irina.moviedbkt.view.screens.home.MainActivity
import ua.shtain.irina.moviedbkt.view.screens.home.common.listeners.EndlessScrollListener
import ua.shtain.irina.moviedbkt.view.screens.home.common.listeners.OnCardClickListener
import ua.shtain.irina.moviedbkt.view.screens.home.common.listeners.OnNextPageListener
import ua.shtain.irina.moviedbkt.view.screens.home.common.listeners.StarListener
import ua.shtain.irina.moviedbkt.view.screens.home.stars.adapter.SearchStarAdapter
import ua.shtain.irina.moviedbkt.view.screens.home.stars.adapter.StarDH
import ua.shtain.irina.moviedbkt.view.screens.home.stars.stars_details.StarsDetailsFragment
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by Irina Shtain on 01.03.2018.
 */
class SearchStarFragment : RefreshableFragment(), SearchStarContract.View, StarListener {
    @Inject
    lateinit var mPresenter: SearchStarPresenter
    @Inject
    lateinit var mAdapter: SearchStarAdapter

    override fun getLayoutRes() = R.layout.fragment_search_star

    override fun initGraph() {
        mActivity.mObjectGraph.getHomeComponent().inject(this)
    }

    override fun getPresenter() = mPresenter

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        mPresenter.mView = this
        mPresenter.subscribe()
    }

    private fun initUI() {
        (mActivity as MainActivity).getToolbarMan()?.setTitle(R.string.title_read_about_star)
        tvSearch.setHint(R.string.hint_input_stars_name)
        RxView.clicks(bt_search)
                .throttleFirst(Constants.CLICK_DELAY, TimeUnit.MILLISECONDS)
                .subscribe {
                    mPresenter.onSearchClick(tvSearch.text.toString())
                    hideKeyboard()
                }
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val layoutManager = GridLayoutManager(mActivity, 2)
        rvStars.layoutManager = layoutManager
        mAdapter.setListener(this)
        rvStars.adapter = mAdapter
        rvStars.addOnScrollListener(EndlessScrollListener(layoutManager, object : OnNextPageListener {
            override fun onLoadMore(): Boolean {
                mPresenter.getNextPage()
                return true
            }
        }))
    }

    override fun onStarClick(starItem: StarItem) {
       mActivity.changeFragment(StarsDetailsFragment.newInstance(starItem.id, starItem.knownFor))
    }

    override fun setList(starDHs: ArrayList<StarDH>) {
        rvStars.visibility = View.VISIBLE
        mAdapter.setListDH(starDHs)
    }

    override fun addList(starDHs: ArrayList<StarDH>) {
        mAdapter.addListDH(starDHs)
    }

    override fun showPlaceholder(placeholderType: Constants.PlaceholderType) {
        rlPlaceholder.visibility = View.VISIBLE
        rvStars.visibility = View.GONE
        when (placeholderType) {
            Constants.PlaceholderType.EMPTY -> {
                ivPlaceholderImage.setImageResource(R.drawable.placeholder_empty)
                tvPlaceholderMessage.setText(R.string.error_msg_no_star_with_such_name)
            }
            Constants.PlaceholderType.NETWORK -> {
                ivPlaceholderImage.setImageResource(R.drawable.ic_cloud_off)
                tvPlaceholderMessage.setText(R.string.err_msg_connection_problem)
            }
            Constants.MessageType.UNKNOWN -> {
                ivPlaceholderImage.setImageResource(R.drawable.ic_sentiment_dissatisfied)
                tvPlaceholderMessage.setText(R.string.err_msg_something_goes_wrong)
            }
            else -> super.showPlaceholder(placeholderType)
        }
    }
}