package ua.shtain.irina.moviedbkt.view.screens.home

import android.os.Bundle
import android.support.v7.widget.Toolbar
import kotlinx.android.synthetic.main.activity_main.*
import ua.shtain.irina.moviedbkt.R
import ua.shtain.irina.moviedbkt.view.base.BaseActivity

class MainActivity : BaseActivity() {

    override fun getToolbar(): Toolbar? {
        return toolbar_MA
    }

    override fun getContainer(): Int {
        return R.id.content_MA
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar_MA?.title = "Hello"
    }

    override fun initGraph() {

    }
}
