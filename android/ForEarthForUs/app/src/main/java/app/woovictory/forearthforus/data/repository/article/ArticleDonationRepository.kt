package app.woovictory.forearthforus.data.repository.article

import app.woovictory.forearthforus.data.source.article.ArticleDonationRemoteDataSource
import app.woovictory.forearthforus.model.article.DonationResponse
import io.reactivex.Single
import retrofit2.Response

/**
 * Created by VictoryWoo
 */
class ArticleDonationRepository(private val articleDonationRemoteDataSource: ArticleDonationRemoteDataSource) {

    fun getDonationList(token: String): Single<Response<List<DonationResponse>>> {
        return articleDonationRemoteDataSource.getDonationList(token)
    }
}