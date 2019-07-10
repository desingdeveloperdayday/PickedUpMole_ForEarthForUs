package app.woovictory.forearthforus.vm.article

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import app.woovictory.forearthforus.base.BaseViewModel
import app.woovictory.forearthforus.data.repository.article.ArticleDetailRepository
import app.woovictory.forearthforus.model.article.DonationDetailResponse
import app.woovictory.forearthforus.model.article.ScrapRequest
import app.woovictory.forearthforus.model.mypage.ScrapResponse
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

    private val _scrapResponse = MutableLiveData<ScrapResponse>()
    val scrapResponse: LiveData<ScrapResponse>
        get() = _scrapResponse

    fun clickToBack() {
        _clickToBack.call()
    }

    init {
        _isLoading.value = true
    }

    // 아티클 더보기 리스트 불러오기.
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

    // 스크랩 기능.
    fun postScrapArticle(token: String, scrapRequest: ScrapRequest) {
        addDisposable(
            articleDetailRepository.postScrapArticle(token, scrapRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    if (response.isSuccessful) {
                        response.body()?.let {
                            it.campaign.scrap
                            Log.v("9920 scrap", it.campaign.scrap.toString())
                            _scrapResponse.value = it
                        }
                    }
                    Log.v("9920 scrap s", response.code().toString())
                }, { error ->
                    Log.v("9920 scrap f", error.message)
                })
        )
    }

    fun deleteScrapArticle(token: String, id: String) {
        addDisposable(articleDetailRepository.deleteScrapArticle(token, id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.v("002031 Success", "Success")
            }, { error ->
                Log.v("002031 error", error.message)
            })
        )
    }

}