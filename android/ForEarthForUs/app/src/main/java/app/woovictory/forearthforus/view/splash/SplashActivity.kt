package app.woovictory.forearthforus.view.splash

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import app.woovictory.forearthforus.view.MainActivity
import app.woovictory.forearthforus.R
import app.woovictory.forearthforus.data.SharedPreferenceManager
import app.woovictory.forearthforus.view.account.LoginActivity
import app.woovictory.forearthforus.view.intro.OnBoardingActivity
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import org.jetbrains.anko.startActivity
import java.util.concurrent.TimeUnit
import app.woovictory.forearthforus.utils.TAG

/*
* TODO : 스플래쉬 로티 애니메이션 적용하기.
* */
class SplashActivity : AppCompatActivity() {

    private val compositeDisposable = CompositeDisposable()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        compositeDisposable.add(
            Observable.timer(2000L, TimeUnit.MILLISECONDS)
                .subscribe({
                    checkSavedToken()
                }, { error ->
                    Log.v(TAG, error.message)
                })
        )
    }

    private fun checkSavedToken() {
        // 토큰이 존재할 때!
        if (SharedPreferenceManager.token.isNotBlank()) {
            // 온보딩을 본 적이 있다면.
            if (SharedPreferenceManager.userFirstState) {
                startActivity<MainActivity>()
            } else {
                // 토큰이 존재하는데 온보딩을 안 본 적은 있을 수 없다.
                Log.v(TAG, "이쪽은 들어올 수 없다고 본다.")
                startActivity<OnBoardingActivity>()
            }
        } else {
            // 토큰이 존재하지 않을 때!
            // 온보딩을 본 적이 있다면. -> 로그아웃 했을 경우.
            if (SharedPreferenceManager.userFirstState) {
                Log.v(TAG, "토큰이 없고 온보딩을 본 적이 있음.")
                startActivity<LoginActivity>()
            } else {
                // 토큰이 존재하지 않고, 온보딩을 본 적이 없다면 첫 사용자.
                Log.v(TAG, "토큰이 없고 온보딩을 본 적이 없음.")
                startActivity<OnBoardingActivity>()
            }
        }
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.clear()
        }
    }
}