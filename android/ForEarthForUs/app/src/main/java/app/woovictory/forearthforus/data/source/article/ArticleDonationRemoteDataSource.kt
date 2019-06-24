package app.woovictory.forearthforus.data.source.article

import app.woovictory.forearthforus.api.ApiService
import app.woovictory.forearthforus.model.article.DonationResponse
import io.reactivex.Single
import retrofit2.Response

/**
 * Created by VictoryWoo
 */
class ArticleDonationRemoteDataSource(private val api: ApiService) {

    fun getDonationList(token: String): Single<Response<List<DonationResponse>>> {
        return api.getDonationList("JWT $token")
    }
}