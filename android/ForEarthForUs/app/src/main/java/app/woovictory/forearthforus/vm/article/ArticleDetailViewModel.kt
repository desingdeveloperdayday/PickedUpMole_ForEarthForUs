package app.woovictory.forearthforus.vm.article

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import app.woovictory.forearthforus.base.BaseViewModel
import app.woovictory.forearthforus.data.repository.article.ArticleDetailRepository
import app.woovictory.forearthforus.model.article.DonationDetailResponse
import app.woovictory.forearthforus.model.article.ScrapRequest
import app.woovictory.forearthforus.util.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by VictoryWoo
 */
class ArticleDetailViewModel(private val articleDetailRepository: ArticleDetailRepository) : BaseViewModel() {

    private val _clickToBack = SingleLiveEvent<Any>()
    val clickToBack: LiveData<Any>
        get() = _clickToBack

    private val _articleDetailResponse = SingleLiveEvent<List<DonationDetailResponse>>()
    val donationDetailResponse: LiveData<List<DonationDetailResponse>>
        get() = _articleDetailResponse

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    fun clickToBack() {
        Log.v("0099", "들어오니? 1")
        _clickToBack.call()
    }

    init {
        _isLoading.value = true
    }

    fun getDetailList(token: String) {
        _isLoading.value = true
        addDisposable(
            articleDetailRepository.getDetailList(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    if (response.isSuccessful) {
                        response?.let {
                            Log.v("882910", it.code().toString())
                            _articleDetailResponse.value = it.body()
                        }
                    }
                    _isLoading.value = false
                }, { error ->
                    Log.v("error", error.message)
                    _isLoading.value = true
                })
        )
    }

    fun postScrapArticle(token: String, scrapRequest: ScrapRequest){
        addDisposable(
            articleDetailRepository.postScrapArticle(token, scrapRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({response->
                    if(response.isSuccessful){
                        response.body()?.let {
                            it.campaign.scrap
                            Log.v("9920 scrap", it.campaign.scrap.toString())
                        }
                    }
                    Log.v("9920 scrap s", response.code().toString())
                },{error->
                    Log.v("9920 scrap f", error.message)
                })
        )
    }

}