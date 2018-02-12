package ua.shtain.irina.moviedbkt.view.screens.login

import android.os.Bundle
import android.support.v7.widget.Toolbar
import ua.shtain.irina.moviedbkt.R
import ua.shtain.irina.moviedbkt.view.base.BaseActivity
import ua.shtain.irina.moviedbkt.view.screens.login.content.LoginFragment

/**
 * Created by Irina Shtain on 09.02.2018.
 */
class LoginActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)
        changeFragment(LoginFragment(), true)
    }


    override fun getToolbar(): Toolbar? {
        return null
    }

    override fun initGraph() {
        mObjectGraph.getLoginComponent().inject(this)
    }

    override fun getContainer() = R.id.container



}