package app.woovictory.forearthforus.data.repository.article

import app.woovictory.forearthforus.data.source.article.ArticleDetailRemoteDataSource
import app.woovictory.forearthforus.model.article.DonationDetailResponse
import io.reactivex.Single
import retrofit2.Response

/**
 * Created by VictoryWoo
 */
class ArticleDetailRepository(private val articleDetailRemoteDataSource: ArticleDetailRemoteDataSource) {
    fun getDetailList(token: String): Single<Response<List<DonationDetailResponse>>> {
        return articleDetailRemoteDataSource.getDetailList(token)
    }
}