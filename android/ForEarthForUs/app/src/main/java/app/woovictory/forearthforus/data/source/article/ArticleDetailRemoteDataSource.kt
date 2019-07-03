package app.woovictory.forearthforus.data.source.article

import app.woovictory.forearthforus.api.ApiService
import app.woovictory.forearthforus.model.article.DonationDetailResponse
import app.woovictory.forearthforus.model.article.ScrapRequest
import app.woovictory.forearthforus.model.mypage.ScrapResponse
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Response

/**
 * Created by VictoryWoo
 */
class ArticleDetailRemoteDataSource(private val api: ApiService) {
    fun getDetailList(token: String): Single<Response<List<DonationDetailResponse>>> {
        return api.getCampaignList("JWT $token")
    }

    fun postScrapArticle(token: String, scrapRequest: ScrapRequest): Observable<Response<ScrapResponse>> {
        return api.postScrapArticle("JWT $token", scrapRequest)
    }

    fun deleteScrapArticle(token: String, id: String): Completable{
        return api.deleteScrap("JWT $token", id)
    }
}