package ua.shtain.irina.moviedbkt.view.base

import android.content.Context
import android.support.v4.app.Fragment

/**
 * Created by Irina Shtain on 30.01.2018.
 */
abstract class BaseFragment:Fragment(){
    protected lateinit var mActivity: BaseActivity

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mActivity = context as BaseActivity
    }
}