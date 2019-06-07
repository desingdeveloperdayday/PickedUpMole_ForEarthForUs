package app.woovictory.forearthforus.data.repository.account

import app.woovictory.forearthforus.data.source.account.PreferenceRemoteDataSource
import app.woovictory.forearthforus.model.account.PreferenceModel
import io.reactivex.Single
import retrofit2.Response

/**
 * Created by VictoryWoo
 */
class PreferenceRepository(private val preferenceRemoteDataSource: PreferenceRemoteDataSource) {

    fun selectUserPreference(token: String, list: ArrayList<PreferenceModel>)
            : Single<Response<ArrayList<PreferenceModel>>> {
        return preferenceRemoteDataSource.selectUserPreference(token, list)
    }
}