package app.woovictory.forearthforus.data.repository.scrap

import app.woovictory.forearthforus.data.source.scrap.ScrapRemoteDataSource
import app.woovictory.forearthforus.model.mypage.ScrapResponse
import io.reactivex.Single
import retrofit2.Response

/**
 * Created by VictoryWoo
 */
class ScrapRepository(private val scrapRemoteDataSource: ScrapRemoteDataSource) {
    fun getScrapList(token: String): Single<Response<ArrayList<ScrapResponse>>> {
        return scrapRemoteDataSource.getScrapList(token)
    }
}