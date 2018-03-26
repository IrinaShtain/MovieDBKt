package ua.shtain.irina.moviedbkt.view.screens.home.common.tv_shows

import android.os.Bundle
import android.support.annotation.StringRes
import android.support.v7.app.AlertDialog
import android.support.v7.widget.GridLayoutManager
import android.view.View
import android.widget.ImageView
import kotlinx.android.synthetic.main.fragment_tv_shows.*
import ua.shtain.irina.moviedbkt.R
import ua.shtain.irina.moviedbkt.other.Constants
import ua.shtain.irina.moviedbkt.view.base.refresheble_content.RefreshableFragment
import ua.shtain.irina.moviedbkt.view.screens.home.MainActivity
import ua.shtain.irina.moviedbkt.view.screens.home.common.listeners.EndlessScrollListener
import ua.shtain.irina.moviedbkt.view.screens.home.common.listeners.OnDeleteClickListener
import ua.shtain.irina.moviedbkt.view.screens.home.common.listeners.OnNextPageListener
import ua.shtain.irina.moviedbkt.view.screens.home.common.tv_show_details.TvShowDetailsFragment
import ua.shtain.irina.moviedbkt.view.screens.home.common.listeners.OnItemClickListener
import ua.shtain.irina.moviedbkt.view.screens.home.common.tv_shows.adapter.TvShowItemAdapter
import ua.shtain.irina.moviedbkt.view.screens.home.common.tv_shows.adapter.TvShowItemDH

/**
 * Created by Irina Shtain on 02.03.2018.
 */
abstract class TvShowsFragment : RefreshableFragment(), TvShowsContract.View, OnItemClickListener, OnDeleteClickListener {

    protected var mShowsType = 0
    protected var mShowsID = 0
    var mAdapter: TvShowItemAdapter? = null
    protected lateinit var scrollListener: EndlessScrollListener

    abstract fun getSearchPresenter(): TvShowsPresenter
    @StringRes
    abstract fun getToolbarTitle(): Int

    @StringRes
    abstract fun getErrorEmptyText(): Int

    override fun getLayoutRes() = R.layout.fragment_tv_shows

    override fun getPresenter() = getSearchPresenter()

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        (mActivity as MainActivity).getToolbarMan()?.setTitle(getToolbarTitle())
        setupMoviesRecyclerView()
    }

    private fun setupMoviesRecyclerView() {
        val layoutManager = GridLayoutManager(mActivity, 2)
        rvShows.layoutManager = layoutManager
        if (mAdapter == null)
            mAdapter = TvShowItemAdapter()
        mAdapter!!.setListener(this)
        mAdapter!!.setDeleteItemListener(this)
        rvShows.adapter = mAdapter
        scrollListener = EndlessScrollListener(layoutManager, object : OnNextPageListener {
            override fun onLoadMore(): Boolean {
                getSearchPresenter().getNextPage()
                return true
            }
        })
        rvShows.addOnScrollListener(scrollListener)
    }

    override fun onCardClick(imageView: ImageView, itemID: Int, title: String, posterUrl: String) {
        mActivity.changeFragmentWithTransition(TvShowDetailsFragment.newInstance(itemID, posterUrl, title), imageView)
        getSearchPresenter().updateNeedRefresh(false)
    }

    override fun setList(showsDHs: MutableList<TvShowItemDH>) {
        scrollListener.reset()
        mAdapter?.setListDH(showsDHs)
    }

    override fun addList(showsDHs: MutableList<TvShowItemDH>) {
        mAdapter?.addListDH(showsDHs)
    }

    override fun getShowsType() = mShowsType

    override fun getShowID() = mShowsID

    override fun onDeleteItemClick(itemID: Int, position: Int) {
        getSearchPresenter().deleteTvShow(itemID, position)
    }

    override fun showAlert(itemId: Int, position: Int) {
        val builder = AlertDialog.Builder(activity)
        builder.setMessage(R.string.question_about_deleting_tv_show)
        builder.setPositiveButton(R.string.answer_yes) { _, _ -> getSearchPresenter().deletionConfirmed(itemId, position) }
        builder.setNegativeButton(R.string.answer_no, null)

        builder.show()
    }

    override fun updateTvShows(position: Int) {
        mAdapter?.deleteItem(position)
    }

    override fun showPlaceholder(placeholderType: Constants.PlaceholderType) {
        rlPlaceholder.visibility = View.VISIBLE

        when (placeholderType) {
            Constants.PlaceholderType.EMPTY -> {
                ivPlaceholderImage.setImageResource(R.drawable.placeholder_empty)
                tvPlaceholderMessage.setText(getErrorEmptyText())
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