package app.woovictory.forearthforus.vm

import android.util.Log
import androidx.lifecycle.LiveData
import app.woovictory.forearthforus.base.BaseViewModel
import app.woovictory.forearthforus.util.SingleLiveEvent

/**
 * Created by VictoryWoo
 */
class ArticleDetailViewModel : BaseViewModel() {

    private val _clickToBack = SingleLiveEvent<Any>()
    val clickToBack: LiveData<Any>
        get() = _clickToBack

    fun clickToBack() {
        Log.v("0099","들어오니? 1")
        _clickToBack.call()
    }
}