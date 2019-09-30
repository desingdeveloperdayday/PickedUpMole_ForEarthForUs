package app.woovictory.forearthforus.vm.account

import android.util.Log
import androidx.lifecycle.LiveData
import app.woovictory.forearthforus.base.BaseViewModel
import app.woovictory.forearthforus.data.SharedPreferenceManager
import app.woovictory.forearthforus.data.repository.account.LoginRepository
import app.woovictory.forearthforus.model.account.LoginByEmailResponse
import app.woovictory.forearthforus.utils.SingleLiveEvent
import app.woovictory.forearthforus.view.account.LoginContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

/**
 * Created by VictoryWoo
 */
class LoginViewModel(private val loginRepository: LoginRepository) : BaseViewModel(),
    LoginContract.ViewModel {

    private val _clickToLogin = SingleLiveEvent<Any>()
    val clickToLogin: LiveData<Any>
        get() = _clickToLogin

    private val _clickToSignActivity = SingleLiveEvent<Any>()
    val clickToSignActivity: LiveData<Any>
        get() = _clickToSignActivity

    private val _loginResponse = SingleLiveEvent<Boolean>()
    val loginResponse: LiveData<Boolean>
        get() = _loginResponse

    private val _loginParentClick = SingleLiveEvent<Boolean>()
    val loginParentClick: LiveData<Boolean>
        get() = _loginParentClick

    fun clickToLogin() {
        _clickToLogin.call()
    }

    fun clickToSignActivity() {
        _clickToSignActivity.call()
    }

    fun clickToLayout() {
        _loginParentClick.call()
    }

    fun postLogin(email: String, password: String) {
        loginRepository.login(email, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response -> onSuccessLogin(response) }, { onFailLogin(it) })
            .apply { addDisposable(this) }
    }

    override fun onSuccessLogin(response: Response<LoginByEmailResponse>) {
        if (response.isSuccessful) {
            response.body()?.let {
                SharedPreferenceManager.token = it.token
                SharedPreferenceManager.earthLevel = it.user.earthLevel
                SharedPreferenceManager.userName = it.user.name
                SharedPreferenceManager.userId = it.user.id
                SharedPreferenceManager.userEmail = it.user.email
            }
            _loginResponse.value = true
        } else {
            _loginResponse.value = false
        }
    }

    override fun onFailLogin(throwable: Throwable) {
        Log.v("ERROR", throwable.message!!)
    }
}