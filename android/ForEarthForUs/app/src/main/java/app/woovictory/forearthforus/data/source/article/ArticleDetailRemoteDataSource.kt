package app.woovictory.forearthforus.data.source.article

import app.woovictory.forearthforus.api.ApiService
import app.woovictory.forearthforus.model.article.ArticleDetailResponse
import io.reactivex.Single
import retrofit2.Response

/**
 * Created by VictoryWoo
 */
class ArticleDetailRemoteDataSource(private val api: ApiService) {
    fun getDetailList(token: String): Single<Response<List<ArticleDetailResponse>>> {
        return api.getCampaignList("JWT $token")
    }
}