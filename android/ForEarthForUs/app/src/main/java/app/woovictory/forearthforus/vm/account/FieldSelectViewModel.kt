package app.woovictory.forearthforus.vm.account

import android.util.Log
import androidx.lifecycle.LiveData
import app.woovictory.forearthforus.base.BaseViewModel
import app.woovictory.forearthforus.data.repository.account.PreferenceRepository
import app.woovictory.forearthforus.model.account.PreferenceModel
import app.woovictory.forearthforus.util.SharedPreferenceManager
import app.woovictory.forearthforus.util.SingleLiveEvent
import com.google.gson.JsonObject
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by VictoryWoo
 */
class FieldSelectViewModel(private val preferenceRepository: PreferenceRepository) : BaseViewModel() {

    private val _clickToSelectPreference = SingleLiveEvent<Any>()
    val clickToSelectPreference: LiveData<Any>
        get() = _clickToSelectPreference

    private lateinit var list: ArrayList<PreferenceModel>

    fun clickToSelectPreference() {
        _clickToSelectPreference.call()
    }

    fun selectUserPreference() {
        list = ArrayList()
        list.add(PreferenceModel(8, SharedPreferenceManager.userId))
        list.add(PreferenceModel(9, SharedPreferenceManager.userId))



        addDisposable(
            preferenceRepository.selectUserPreference(SharedPreferenceManager.token, list)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    if (response.isSuccessful) {
                        Log.v("8822", response.code().toString())
                    }
                    Log.v("8822 s", response.code().toString())
                }, { e ->
                    Log.v("8822 f", e.message)
                })
        )
    }
}