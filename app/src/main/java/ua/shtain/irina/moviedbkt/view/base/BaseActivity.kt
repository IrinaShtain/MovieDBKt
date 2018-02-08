package ua.shtain.irina.moviedbkt.view.base

import android.os.Bundle
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import ua.shtain.irina.moviedbkt.R
import ua.shtain.irina.moviedbkt.root.ObjectGraph
import javax.inject.Inject

/**
 * Created by Irina Shtain on 30.01.2018.
 */
abstract class BaseActivity: AppCompatActivity() {
    protected abstract fun getToolbar(): Toolbar?
    protected abstract fun init()
    protected lateinit var mObjectGraph: ObjectGraph
    @IdRes
    protected abstract fun getContainer(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mObjectGraph = ObjectGraph.getInstance(application)
        setSupportActionBar(getToolbar())
        init()
    }


    override fun onDestroy() {
        super.onDestroy()
    }

    fun changeFragment(f: Fragment, cleanStack: Boolean = false) {
        val ft = supportFragmentManager.beginTransaction()
        if (cleanStack) {
            clearBackStack()
        }
        ft.setCustomAnimations(
                R.anim.abc_fade_in, R.anim.abc_fade_out, R.anim.abc_popup_enter, R.anim.abc_popup_exit)
        ft.replace(getContainer(), f)
        ft.addToBackStack(null)
        ft.commit()
    }

    fun clearBackStack() {
        val manager = supportFragmentManager
        if (manager.backStackEntryCount > 0) {
            val first = manager.getBackStackEntryAt(0)
            manager.popBackStack(first.id, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
    }

    override fun onBackPressed() {
        val fragmentManager = supportFragmentManager
        if (fragmentManager.backStackEntryCount > 1) {
            fragmentManager.popBackStack()
        } else {
            finish()
        }
    }
}