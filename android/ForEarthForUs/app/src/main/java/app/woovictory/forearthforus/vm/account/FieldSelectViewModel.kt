package app.woovictory.forearthforus.vm.account

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import app.woovictory.forearthforus.base.BaseViewModel
import app.woovictory.forearthforus.data.repository.account.PreferenceRepository
import app.woovictory.forearthforus.util.SharedPreferenceManager
import app.woovictory.forearthforus.util.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by VictoryWoo
 */
class FieldSelectViewModel(private val preferenceRepository: PreferenceRepository) : BaseViewModel() {

    private val _clickToSelectPreference = SingleLiveEvent<Any>()
    val clickToSelectPreference: LiveData<Any>
        get() = _clickToSelectPreference

    private val _preferenceResponse = MutableLiveData<Boolean>()
    val preferenceResponse: LiveData<Boolean>
        get() = _preferenceResponse

    fun clickToSelectPreference() {
        _clickToSelectPreference.call()
    }

    fun selectUserPreference(preferList: MutableList<Int>) {

        Log.v("8822 type ", preferList.toString())

        addDisposable(
            preferenceRepository.selectUserPreference(SharedPreferenceManager.token, preferList)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.v("8822 s", "success")
                    _preferenceResponse.value = true
                }, { error ->
                    Log.v("Field Select error", error.message)
                    _preferenceResponse.value = false
                })
        )

    }
}