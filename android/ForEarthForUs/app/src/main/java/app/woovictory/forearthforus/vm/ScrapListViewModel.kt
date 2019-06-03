package app.woovictory.forearthforus.vm

import androidx.lifecycle.LiveData
import app.woovictory.forearthforus.base.BaseViewModel
import app.woovictory.forearthforus.util.SingleLiveEvent

/**
 * Created by VictoryWoo
 */
class ScrapListViewModel : BaseViewModel() {
    private val _clickToBack = SingleLiveEvent<Any>()
    val clickToBack: LiveData<Any>
        get() = _clickToBack

    fun clickToBack() {
        _clickToBack.call()
    }
}