package app.woovictory.forearthforus.data.source.article

import app.woovictory.forearthforus.api.ApiService
import app.woovictory.forearthforus.model.article.DonationDetailResponse
import io.reactivex.Single
import retrofit2.Response

/**
 * Created by VictoryWoo
 */
class ArticleDetailRemoteDataSource(private val api: ApiService) {
    fun getDetailList(token: String): Single<Response<List<DonationDetailResponse>>> {
        return api.getCampaignList("JWT $token")
    }
}