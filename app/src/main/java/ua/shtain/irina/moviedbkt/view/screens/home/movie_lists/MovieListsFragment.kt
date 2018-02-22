package ua.shtain.irina.moviedbkt.view.screens.home.movie_lists

import android.app.Activity
import android.content.Intent
import android.graphics.*
import android.os.Bundle
import android.support.annotation.DrawableRes
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.fragment_recycler_view.*
import kotlinx.android.synthetic.main.view_content_refreshable.*
import kotlinx.android.synthetic.main.view_placeholder.*
import ua.shtain.irina.moviedbkt.R
import ua.shtain.irina.moviedbkt.other.Constants
import ua.shtain.irina.moviedbkt.view.base.refresheble_content.RefreshableFragment
import ua.shtain.irina.moviedbkt.view.base.refresheble_content.RefreshablePresenter
import ua.shtain.irina.moviedbkt.view.screens.home.common.listeners.EndlessScrollListener
import ua.shtain.irina.moviedbkt.view.screens.home.common.listeners.OnCardClickListener
import ua.shtain.irina.moviedbkt.view.screens.home.common.listeners.OnNextPageListener
import ua.shtain.irina.moviedbkt.view.screens.home.MainActivity
import ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.adapter.CreatedListsAdapter
import ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.adapter.CreatedListsDH
import ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.add_list.CreateNewListDialog
import ua.shtain.irina.moviedbkt.view.screens.home.movie_lists.movies_in_list.MoviesInListFragment
import javax.inject.Inject

/**
 * Created by Irina Shtain on 15.02.2018.
 */
class MovieListsFragment : RefreshableFragment(), MovieListsContract.View, OnCardClickListener {

    @Inject
    lateinit var mPresenter: MovieListsPresenter
    var newListDialog: CreateNewListDialog? = null


    @Inject
    lateinit var listAdapter: CreatedListsAdapter

    override fun getLayoutRes(): Int {
        return R.layout.fragment_recycler_view
    }

    override fun initGraph() {
        mActivity.mObjectGraph.getHomeComponent().inject(this)
    }

    override fun getPresenter() = mPresenter as RefreshablePresenter


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupSwipeToRemove()
        setupFab()
        mPresenter.mView = this
        mPresenter.subscribe()
    }

    override fun onStart() {
        super.onStart()
        (mActivity as MainActivity).getToolbarMan()?.setTitle(R.string.title_my_lists)
    }

    override fun openListDetails(lisID: Int, listsName: String) {
         mActivity.changeFragment(MoviesInListFragment.newInstance(lisID, listsName))
    }


    override fun deleteItem(pos: Int) {
        listAdapter.deleteItem(pos)
    }

    private fun setupFab() {
        fabAdd_VC.visibility = View.VISIBLE
        fabAdd_VC.setOnClickListener({ v ->
            Log.e("myLog", "onClick FAB ")
            newListDialog = CreateNewListDialog()
            newListDialog!!.setTargetFragment(this, Constants.REQUEST_CODE_CREATE_NEW_LIST)
            newListDialog!!.show(mActivity.supportFragmentManager, "create_list")
        })

    }

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(context)
        rvLists!!.layoutManager = layoutManager
        listAdapter.setListener(this)
        rvLists.adapter = listAdapter
        rvLists.addOnScrollListener(EndlessScrollListener(layoutManager, object : OnNextPageListener {
            override fun onLoadMore(): Boolean {
                mPresenter.getNextPage()
                return true
            }
        }))
    }

    override fun onCardClick(itemID: Int, position: Int) {
        mPresenter.showDetails(itemID, listAdapter.getItem(position))
    }

    private fun setupSwipeToRemove() {
        val simpleItemTouchCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val pos = viewHolder.adapterPosition
                mPresenter.removeList(listAdapter.getItem(pos), pos)
            }

            override fun onChildDraw(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
                val icon = getVectorBitmap(R.drawable.ic_delete_white)
                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                    val itemView = viewHolder.itemView
                    val height = itemView.bottom.toFloat() - itemView.top.toFloat()
                    val width = height / 3

                    val p = Paint()

                    if (dX < 0) {
                        p.color = Color.parseColor("#f44336")
                        val background = RectF(itemView.right.toFloat() + dX, itemView.top.toFloat(), itemView.right.toFloat(), itemView.bottom.toFloat())
                        c.drawRect(background, p)
                        c.clipRect(background)
                        val iconDest = RectF(itemView.right.toFloat() - 2 * width, itemView.top.toFloat() + width, itemView.right.toFloat() - width, itemView.bottom.toFloat() - width)
                        c.drawBitmap(icon, null, iconDest, p)
                    }
                }
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            }
        }
        val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
        itemTouchHelper.attachToRecyclerView(rvLists)
    }

    private fun getVectorBitmap(@DrawableRes drawableId: Int): Bitmap {
        val drawable = ContextCompat.getDrawable(mActivity, drawableId)
        val bitmap = Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        return bitmap
    }

    override fun setLists(createdListsDHs: ArrayList<CreatedListsDH>) {
        listAdapter.setListDH(createdListsDHs)
    }

    override fun addLists(createdListsDHs: ArrayList<CreatedListsDH>) {
        listAdapter.addListDH(createdListsDHs)
    }

    override fun addItem(createdListsDH: CreatedListsDH) {
        listAdapter.addItem(createdListsDH)
    }


    override fun showPlaceholder(placeholderType: Constants.PlaceholderType) {
        super.showPlaceholder(placeholderType)
        if (placeholderType === Constants.PlaceholderType.EMPTY) {
            ivPlaceholderImage_VC.setImageResource(R.drawable.placeholder_empty)
            tvPlaceholderMessage_VC.setText(R.string.err_msg_no_lists)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == Constants.REQUEST_CODE_CREATE_NEW_LIST) {
            mPresenter.showResult(data!!.getIntExtra(Constants.KEY_ERROR_CODE, Constants.ERROR_CODE_UNKNOWN),
                    data.getStringExtra(Constants.KEY_TITLE), data.getStringExtra(Constants.KEY_DESCRIPTION))

        }
    }


}
