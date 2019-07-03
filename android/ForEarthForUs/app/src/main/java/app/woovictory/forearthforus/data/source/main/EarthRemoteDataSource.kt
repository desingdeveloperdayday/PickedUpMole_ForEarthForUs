package app.woovictory.forearthforus.data.source.main

import app.woovictory.forearthforus.api.ApiService
import app.woovictory.forearthforus.model.earth.EarthResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Response

/**
 * Created by VictoryWoo
 */
class EarthRemoteDataSource(val api: ApiService) {
    fun getEarthInformation(token: String, earthLevel: Int): Observable<Response<EarthResponse>> {
        return api.getEarthList("JWT $token", earthLevel)
    }

    fun getUserInformation(token: String): Observable<Response<EarthResponse>>{
        return api.getUserEarthInformation("JWT $token")
    }
}