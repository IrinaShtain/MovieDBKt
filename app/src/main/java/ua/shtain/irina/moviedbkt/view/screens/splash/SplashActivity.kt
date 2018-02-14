package ua.shtain.irina.moviedbkt.view.screens.splash

import android.animation.*
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_splash.*
import ua.shtain.irina.moviedbkt.R
import ua.shtain.irina.moviedbkt.view.base.BaseActivity
import ua.shtain.irina.moviedbkt.view.screens.home.MainActivity
import ua.shtain.irina.moviedbkt.view.screens.login.LoginActivity
import javax.inject.Inject


/**
 * Created by Irina Shtain on 30.01.2018.
 */
class SplashActivity : BaseActivity(), SplashContract.SplashView {

    private var animatorSet = AnimatorSet()
    @Inject
    lateinit var presenter: SplashPresenter

    override fun initGraph() {
        mObjectGraph.getSplashComponent().inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        presenter.mView = this
        presenter.subscribe()
    }

    override fun runSplashAnimation() {
        val fadeLogo = ObjectAnimator.ofFloat(ivLogo, View.ALPHA, 0f, 0.2f, 0.4f, 0.6f, 1f)
        fadeLogo.addUpdateListener({ if (ivLogo.visibility == View.INVISIBLE) ivLogo.visibility = View.VISIBLE })

        val scaleX = ObjectAnimator.ofFloat(ivLogo, View.SCALE_X, 0.8f, 1.2f, 1f, 1.1f)
        val scaleY = ObjectAnimator.ofFloat(ivLogo, View.SCALE_Y, 0.8f, 1.2f, 1f, 1.1f)

        animatorSet.duration = 1500
        animatorSet.play(fadeLogo)
                .with(scaleX)
                .with(scaleY)
                .before(ValueAnimator.ofInt(0, 0).setDuration(1500))
        animatorSet.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                presenter.startNextScreen()
            }
        })

        animatorSet.start()

    }

    override fun startHomeScreen() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }

    override fun startLoginScreen() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }


    override fun getToolbar(): Toolbar? {
        return null
    }

    override fun getContainer(): Int {
        return 0
    }

    override fun onDestroy() {
        super.onDestroy()
        if (animatorSet.isRunning) animatorSet.cancel()
    }
}