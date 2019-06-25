package app.woovictory.forearthforus.data.repository.account

import app.woovictory.forearthforus.data.source.account.PreferenceRemoteDataSource
import io.reactivex.Completable

/**
 * Created by VictoryWoo
 */
class PreferenceRepository(private val preferenceRemoteDataSource: PreferenceRemoteDataSource) {

    fun selectUserPreference(token: String, preferList: MutableList<Int>): Completable {
        return preferenceRemoteDataSource.selectUserPreference(token, preferList)
    }

}