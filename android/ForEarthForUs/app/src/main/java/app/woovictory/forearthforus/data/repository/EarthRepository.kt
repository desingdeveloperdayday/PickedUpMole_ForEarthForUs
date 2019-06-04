package app.woovictory.forearthforus.data.repository

import app.woovictory.forearthforus.data.source.main.EarthRemoteDataSource
import app.woovictory.forearthforus.model.earth.EarthResponse
import io.reactivex.Single

/**
 * Created by VictoryWoo
 */
class EarthRepository(private val earthRemoteDataSource: EarthRemoteDataSource) {

    fun getEarthInformation(token: String, earthLevel: Int): Single<EarthResponse> {
        return earthRemoteDataSource.getEarthInformation(token, earthLevel)
    }
}