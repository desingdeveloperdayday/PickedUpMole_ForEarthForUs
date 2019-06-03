package app.woovictory.forearthforus.vm

import androidx.lifecycle.LiveData
import app.woovictory.forearthforus.base.BaseViewModel
import app.woovictory.forearthforus.util.SingleLiveEvent

/**
 * Created by VictoryWoo
 */
class MainViewModel : BaseViewModel() {
    private val _clickToEarthDetail = SingleLiveEvent<Any>()
    val clickToEarthDetail: LiveData<Any>
        get() = _clickToEarthDetail

    fun clickToDetail(){
        _clickToEarthDetail.call()
    }
}