package app.woovictory.forearthforus.vm.mypage

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import app.woovictory.forearthforus.base.BaseViewModel
import app.woovictory.forearthforus.data.repository.scrap.ScrapRepository
import app.woovictory.forearthforus.model.mypage.ScrapResponse
import app.woovictory.forearthforus.util.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by VictoryWoo
 */
class ScrapListViewModel(private val scrapRepository: ScrapRepository) : BaseViewModel() {
    private val _clickToBack = SingleLiveEvent<Any>()
    val clickToBack: LiveData<Any>
        get() = _clickToBack

    private val _scrapResponse = MutableLiveData<ArrayList<ScrapResponse>>()
    val scrapResponse: LiveData<ArrayList<ScrapResponse>>
        get() = _scrapResponse

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    init {
        _isLoading.value = true
    }

    fun clickToBack() {
        _clickToBack.call()
    }

    fun getScrapList(token: String) {
        _isLoading.value = true
        addDisposable(
            scrapRepository.getScrapList(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    if (response.isSuccessful) {
                        response.body()?.let { items ->
                            Log.v("12351", items.size.toString())
                            _scrapResponse.value = items
                        }
                    }
                    _isLoading.value = false
                }, { error ->
                    Log.v("12351", error.message)
                    _isLoading.value = true
                })
        )
    }
}