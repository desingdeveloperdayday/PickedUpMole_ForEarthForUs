package app.woovictory.forearthforus.data.repository.article

import app.woovictory.forearthforus.data.source.article.ArticleDetailRemoteDataSource
import app.woovictory.forearthforus.model.article.ArticleDetailResponse
import io.reactivex.Single
import retrofit2.Response

/**
 * Created by VictoryWoo
 */
class ArticleDetailRepository(private val articleDetailRemoteDataSource: ArticleDetailRemoteDataSource) {
    fun getDetailList(token: String): Single<Response<List<ArticleDetailResponse>>> {
        return articleDetailRemoteDataSource.getDetailList(token)
    }
}