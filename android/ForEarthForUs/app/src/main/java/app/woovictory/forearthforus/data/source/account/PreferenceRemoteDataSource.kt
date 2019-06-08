package app.woovictory.forearthforus.data.source.account

import app.woovictory.forearthforus.api.ApiService
import app.woovictory.forearthforus.model.account.PreferenceModel
import io.reactivex.Single
import retrofit2.Response

/**
 * Created by VictoryWoo
 */
class PreferenceRemoteDataSource(val api: ApiService) {

    fun selectUserPreference(token: String, list: ArrayList<PreferenceModel>)
            : Single<Response<ArrayList<PreferenceModel>>> {
        return api.selectPreference("JWT $token", list)
    }
}