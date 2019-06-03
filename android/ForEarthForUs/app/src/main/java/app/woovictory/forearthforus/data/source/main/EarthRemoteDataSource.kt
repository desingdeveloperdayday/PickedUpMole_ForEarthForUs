package app.woovictory.forearthforus.data.source.main

import app.woovictory.forearthforus.api.ApiService
import app.woovictory.forearthforus.model.earth.EarthResponse
import app.woovictory.forearthforus.util.SharedPreferenceManager
import io.reactivex.Single

/**
 * Created by VictoryWoo
 */
class EarthRemoteDataSource(val api: ApiService) {

    fun getEarthInformation(): Single<EarthResponse>{
        return api.getEarthList(SharedPreferenceManager.token!!,2)
    }
}