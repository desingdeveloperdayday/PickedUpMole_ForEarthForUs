package app.woovictory.forearthforus.vm.mypage

import androidx.lifecycle.LiveData
import app.woovictory.forearthforus.base.BaseViewModel
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

    private val _clickToScrapList = SingleLiveEvent<Any>()
    val clickToScrapList: LiveData<Any>
        get() = _clickToScrapList

    private val _clickToAlarm = SingleLiveEvent<Any>()
    val clickToAlarm: LiveData<Any>
        get() = _clickToAlarm

    fun clickToAchieve() {
        _clickToAchieve.call()
    }

    fun clickToLogOut() {
        _clickToLogOut.call()
    }

    fun clickToScrapList() {
        _clickToScrapList.call()
    }

    fun clickToAlarm(){
        _clickToAlarm.call()
    }

}