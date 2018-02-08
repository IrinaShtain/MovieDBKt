package ua.shtain.irina.moviedbkt.view.base.content

import android.support.v4.app.Fragment
import ua.shtain.irina.moviedbkt.view.base.IBasePresenter

/**
 * Created by Irina Shtain on 30.01.2018.
 */
abstract class ContentFragment : Fragment(){
    protected abstract fun getLayoutRes(): Int

    protected abstract fun getPresenter(): IBasePresenter?
}