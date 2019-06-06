package app.woovictory.forearthforus.vm

import android.util.Log
import androidx.lifecycle.LiveData
import app.woovictory.forearthforus.base.BaseViewModel
import app.woovictory.forearthforus.data.repository.user.SignUpRepository
import app.woovictory.forearthforus.util.SharedPreferenceManager
import app.woovictory.forearthforus.util.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by VictoryWoo
 */
class SignUpViewModel(private val signUpRepository: SignUpRepository) : BaseViewModel() {

    private val _clickToSignUp = SingleLiveEvent<Any>()
    val clickToSignUp: LiveData<Any>
        get() = _clickToSignUp

    private val _signUpResponse = SingleLiveEvent<Boolean>()
    val signUpResponse: LiveData<Boolean>
        get() = _signUpResponse

    fun clickToSignUp() {
        _clickToSignUp.call()
    }

    fun postSignUp(email: String, name: String, password1: String, password2: String) {
        addDisposable(
            signUpRepository.signUp(email, name, password1, password2)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.v("success: ${it.token}", "990")
                    SharedPreferenceManager.token = it.token
                    Log.v("success token: ${SharedPreferenceManager.token}", "9901")
                    _signUpResponse.value = true

                }, {
                    Log.v("fail: ${it.message}", "990")
                })
        )


    }
}