package app.woovictory.forearthforus.vm

import androidx.lifecycle.LiveData
import app.woovictory.forearthforus.base.BaseViewModel
import app.woovictory.forearthforus.util.SharedPreferenceManager
import app.woovictory.forearthforus.util.SingleLiveEvent

/**
 * Created by VictoryWoo
 */
class MyPageViewModel : BaseViewModel() {

    private val _clickToAchieve = SingleLiveEvent<Any>()
    val clickToAchieve: LiveData<Any>
        get() = _clickToAchieve

    private val _clickToLogOut = SingleLiveEvent<Any>()
    val clickToLogOut: LiveData<Any>
        get() = _clickToLogOut


    fun clickToAchieve() {
        _clickToAchieve.call()
    }

    fun clickToLogOut() {
        _clickToLogOut.call()
    }


}