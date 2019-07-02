package app.woovictory.forearthforus.data.source.scrap

import app.woovictory.forearthforus.api.ApiService
import app.woovictory.forearthforus.model.mypage.ScrapResponse
import io.reactivex.Single
import retrofit2.Response

/**
 * Created by VictoryWoo
 */
class ScrapRemoteDataSource(private val api: ApiService) {
    fun getScrapList(token: String): Single<Response<ArrayList<ScrapResponse>>> {
        return api.getScrapList("JWT $token")
    }
}