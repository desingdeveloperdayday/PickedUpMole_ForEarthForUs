package app.woovictory.forearthforus.data.source.article

import app.woovictory.forearthforus.api.ApiService
import app.woovictory.forearthforus.model.article.ArticleResponse
import io.reactivex.Single
import retrofit2.Response

/**
 * Created by VictoryWoo
 */
class ArticleRemoteDataSource(private val api: ApiService) {
    fun getArticleList(token: String): Single<Response<List<ArticleResponse>>> {
        return api.getArticleList("JWT $token")
    }
}