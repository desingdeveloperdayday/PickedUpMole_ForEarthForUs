package app.woovictory.forearthforus.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import app.woovictory.forearthforus.base.BaseViewModel
import app.woovictory.forearthforus.data.repository.EarthRepository
import app.woovictory.forearthforus.model.earth.EarthResponse
import app.woovictory.forearthforus.util.SharedPreferenceManager
import app.woovictory.forearthforus.util.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by VictoryWoo
 */
class MainViewModel(private val earthRepository: EarthRepository) : BaseViewModel() {

    private val _earthResponse = MutableLiveData<EarthResponse>()
    val earthResponse: LiveData<EarthResponse>
        get() = _earthResponse


    fun getEarthInformation() {
        addDisposable(
            earthRepository
                .getEarthInformation(SharedPreferenceManager.token, SharedPreferenceManager.earthLevel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _earthResponse.value = it


                    Log.v("11882 ${it.earthLevel}", it.earthLevel.toString())
                    Log.v("11882", it.content)
                    Log.v("11882", it.image)
                }, {
                    Log.v("11882", it.message)
                })
        )
    }

    // mutable 하게 만들고, immutable 하게 노출시킨다.


    // earthLevel
    private val _earthLevel = MutableLiveData<Int>()
    val earthLevel: LiveData<Int>
        get() = _earthLevel


    private val _earthImage = MutableLiveData<String>()
    val earthImage: LiveData<String>
        get() = _earthImage


    private val _clickToEarthDetail = SingleLiveEvent<Any>()
    val clickToEarthDetail: LiveData<Any>
        get() = _clickToEarthDetail

    fun clickToDetail() {
        _clickToEarthDetail.call()
    }
}