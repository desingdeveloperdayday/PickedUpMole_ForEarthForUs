package app.woovictory.forearthforus.vm.article

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import app.woovictory.forearthforus.base.BaseViewModel
import app.woovictory.forearthforus.data.repository.article.ArticleDonationRepository
import app.woovictory.forearthforus.data.repository.article.ArticleRepository
import app.woovictory.forearthforus.model.article.ArticleResponse
import app.woovictory.forearthforus.model.article.DonationResponse
import app.woovictory.forearthforus.util.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by VictoryWoo
 * 도네이션, 아티클 리스트를 불러오는 ViewModel
 */
class ArticleViewModel(
    private val articleDonationRepository: ArticleDonationRepository,
    private val articleRepository: ArticleRepository
) : BaseViewModel() {

    // 내부에서는 변경할 수 있지만 외부에서는 변경할 수 없도록 LiveData 를 사용.
    private val _clickToArticleEarthDetail = SingleLiveEvent<Any>()
    val clickToArticleEarthDetail: LiveData<Any>
        get() = _clickToArticleEarthDetail

    private val _clickToArticleUsDetail = SingleLiveEvent<Any>()
    val clickToArticleUsDetail: LiveData<Any>
        get() = _clickToArticleUsDetail

    private val _donationResponse = MutableLiveData<List<DonationResponse>>()
    val donationResponse: LiveData<List<DonationResponse>>
        get() = _donationResponse

    private val _articleResponse = MutableLiveData<List<ArticleResponse>>()
    val articleResponse: LiveData<List<ArticleResponse>>
        get() = _articleResponse

    fun clickToArticleEarthDetail() {
        _clickToArticleEarthDetail.call()
    }

    fun clickToArticleUsDetail() {
        _clickToArticleUsDetail.call()
    }

    fun getDonationList(token: String) {
        addDisposable(
            articleDonationRepository.getDonationList(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    if (response.isSuccessful) {
                        response?.let {
                            Log.v("220132 donation", response.code().toString())
                            _donationResponse.value = it.body()
                        }
                    }
                }, { error ->
                    Log.v("error", error.message)
                })
        )
    }

    fun getArticleList(token: String) {
        addDisposable(
            articleRepository.getArticleList(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    if (response.isSuccessful) {
                        response?.let {
                            Log.v("220132 article", response.code().toString())
                            _articleResponse.value = it.body()
                        }
                    }
                }, { error ->
                    Log.v("Article Error", error.message)
                })
        )
    }


}