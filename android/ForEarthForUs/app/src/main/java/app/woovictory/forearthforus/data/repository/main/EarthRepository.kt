package app.woovictory.forearthforus.data.repository.main

import app.woovictory.forearthforus.data.source.main.EarthRemoteDataSource
import app.woovictory.forearthforus.model.earth.EarthResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Response

/**
 * Created by VictoryWoo
 */
class EarthRepository(private val earthRemoteDataSource: EarthRemoteDataSource) {

    fun getEarthInformation(token: String, earthLevel: Int): Observable<Response<EarthResponse>> {
        return earthRemoteDataSource.getEarthInformation(token, earthLevel)
    }
}