package app.woovictory.forearthforus.vm.article

import androidx.lifecycle.LiveData
import app.woovictory.forearthforus.base.BaseViewModel
import app.woovictory.forearthforus.util.SingleLiveEvent

/**
 * Created by VictoryWoo
 */
class ArticleViewModel : BaseViewModel() {

    // 내부에서는 변경할 수 있지만 외부에서는 변경할 수 없도록 LiveData 를 사용.
    private val _clickToArticleEarthDetail = SingleLiveEvent<Any>()
    val clickToArticleEarthDetail: LiveData<Any>
        get() = _clickToArticleEarthDetail
    private val _clickToArticleUsDetail = SingleLiveEvent<Any>()
    val clickToArticleUsDetail: LiveData<Any>
        get() = _clickToArticleUsDetail

    fun clickToArticleEarthDetail() {
        _clickToArticleEarthDetail.call()
    }

    fun clickToArticleUsDetail(){
        _clickToArticleUsDetail.call()
    }
}