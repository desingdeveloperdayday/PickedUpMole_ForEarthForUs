package app.woovictory.forearthforus.data.source.main

import app.woovictory.forearthforus.api.ApiService
import app.woovictory.forearthforus.model.earth.EarthResponse
import io.reactivex.Single

/**
 * Created by VictoryWoo
 */
class EarthRemoteDataSource(val api: ApiService) {
    fun getEarthInformation(token: String, earthLevel: Int): Single<EarthResponse> {
        return api.getEarthList("JWT $token", earthLevel)
    }
}