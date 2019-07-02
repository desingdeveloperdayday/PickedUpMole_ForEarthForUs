package app.woovictory.forearthforus.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import app.woovictory.forearthforus.MainActivity
import app.woovictory.forearthforus.R
import app.woovictory.forearthforus.util.SharedPreferenceManager
import app.woovictory.forearthforus.view.account.LoginActivity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_splash.*
import org.jetbrains.anko.startActivity
import java.util.concurrent.TimeUnit

class SplashActivity : AppCompatActivity() {

    lateinit var fadeInAnimation: Animation
    lateinit var handler: Handler
    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in)


        /*  Observable.interval(500L, TimeUnit.MILLISECONDS)
              .map { value ->
                  value.toInt()
              }
              .take(3)
              .subscribeOn(Schedulers.io())
              .observeOn(AndroidSchedulers.mainThread())
              .subscribe({
                  showImage(it)
              }, {
                  Log.v("sas1", it.message)

              })*/

        Observable.timer(2000L, TimeUnit.MILLISECONDS)
            .subscribe({
                checkSavedToken()
            }, { error ->
                Log.v("tag" + 19992, error.message)
            })
    }

    private fun checkSavedToken() {
        if (SharedPreferenceManager.token.isNotBlank()) {
            Log.d("tag" + 19992, "token exist")
            Log.d("tag" + 19992, SharedPreferenceManager.token.isBlank().toString())
            startActivity<MainActivity>()
        } else {
            startActivity<LoginActivity>()
            Log.d("tag" + 19992, "No token")
        }
        finish()
    }

    private fun showImage(value: Int) {
        when (value) {
            0 -> {
                splashOneImage.visibility = View.VISIBLE
                splashOneImage.startAnimation(fadeInAnimation)
            }
            1 -> {
                splashTwoImage.visibility = View.VISIBLE
                splashTwoImage.startAnimation(fadeInAnimation)
            }
            2 -> {
                splashThreeImage.visibility = View.VISIBLE
                splashThreeImage.startAnimation(fadeInAnimation)
            }
        }
    }
}