package app.woovictory.forearthforus.data.repository.article

import app.woovictory.forearthforus.data.source.article.ArticleRemoteDataSource
import app.woovictory.forearthforus.model.article.ArticleResponse
import io.reactivex.Single
import retrofit2.Response

/**
 * Created by VictoryWoo
 */
class ArticleRepository(private val articleRemoteDataSource: ArticleRemoteDataSource) {
    fun getArticleList(token: String): Single<Response<List<ArticleResponse>>> {
        return articleRemoteDataSource.getArticleList(token)
    }
}