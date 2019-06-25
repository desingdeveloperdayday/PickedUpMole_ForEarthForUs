package app.woovictory.forearthforus.vm.account

import android.util.Log
import androidx.lifecycle.LiveData
import app.woovictory.forearthforus.base.BaseViewModel
import app.woovictory.forearthforus.data.repository.account.LoginRepository
import app.woovictory.forearthforus.util.SharedPreferenceManager
import app.woovictory.forearthforus.util.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by VictoryWoo
 */
class LoginViewModel(private val loginRepository: LoginRepository) : BaseViewModel() {
    private val _clickToLogin = SingleLiveEvent<Any>()
    val clickToLogin: LiveData<Any>
        get() = _clickToLogin

    private val _clickToSignActivity = SingleLiveEvent<Any>()
    val clickToSignActivity: LiveData<Any>
        get() = _clickToSignActivity

  /*  private val _clickToGoogleLogin = SingleLiveEvent<Any>()
    val clickToGoogleLogin: LiveData<Any>
        get() = _clickToGoogleLogin*/

    private val _loginResponse = SingleLiveEvent<Boolean>()
    val loginResponse: LiveData<Boolean>
        get() = _loginResponse

    fun clickToLogin() {
        _clickToLogin.call()
    }

    fun clickToSignActivity() {
        _clickToSignActivity.call()
    }

 /*   fun clickToGoogleLogin() {
        _clickToGoogleLogin.call()
    }*/

    fun postLogin(email: String, password: String) {
        addDisposable(
            loginRepository.login(email, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    if (response.isSuccessful) {
                        response.body()?.let {
                            SharedPreferenceManager.token = it.token
                            SharedPreferenceManager.earthLevel = it.user.earthLevel
                            SharedPreferenceManager.userName = it.user.name
                            SharedPreferenceManager.userId = it.user.id
                            SharedPreferenceManager.userEmail = it.user.email

                        }
                        _loginResponse.value = true
                    }

                    Log.v("success login 9871", response.code().toString())

                }, {
                    Log.v("fail login 9871", it.message)
                })
        )
    }
}