package app.woovictory.forearthforus.data.repository.article

import app.woovictory.forearthforus.data.source.article.ArticleDetailRemoteDataSource
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
class ArticleDetailRepository(private val articleDetailRemoteDataSource: ArticleDetailRemoteDataSource) {
    fun getDetailList(token: String): Single<Response<List<DonationDetailResponse>>> {
        return articleDetailRemoteDataSource.getDetailList(token)
    }

    fun postScrapArticle(token: String, scrapRequest: ScrapRequest): Observable<Response<ScrapResponse>> {
        return articleDetailRemoteDataSource.postScrapArticle(token, scrapRequest)
    }

    fun deleteScrapArticle(token: String, id: String): Completable {
        return articleDetailRemoteDataSource.deleteScrapArticle(token, id)
    }
}