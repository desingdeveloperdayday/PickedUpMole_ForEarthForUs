package app.woovictory.forearthforus.vm

import android.util.Log
import androidx.lifecycle.LiveData
import app.woovictory.forearthforus.base.BaseViewModel
import app.woovictory.forearthforus.data.repository.SignUpRepository
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
                }, {
                    Log.v("fail: ${it.message}", "990")
                })

        )


    }
}