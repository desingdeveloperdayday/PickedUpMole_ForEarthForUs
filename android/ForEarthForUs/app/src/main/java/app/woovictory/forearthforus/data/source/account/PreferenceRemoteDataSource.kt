package app.woovictory.forearthforus.data.source.account

import app.woovictory.forearthforus.api.ApiService
import io.reactivex.Completable

/**
 * Created by VictoryWoo
 */
class PreferenceRemoteDataSource(val api: ApiService) {

    fun selectUserPreference(token: String, preferList: MutableList<Int>): Completable {
        return api.selectPreference("JWT $token", preferList)
    }
}