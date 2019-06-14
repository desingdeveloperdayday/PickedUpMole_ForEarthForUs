package app.woovictory.forearthforus.data.repository.mission

import app.woovictory.forearthforus.data.source.mission.MissionDetailRemoteDataSource
import app.woovictory.forearthforus.model.mission.MissionDetailResponse
import io.reactivex.Single
import retrofit2.Response

/**
 * Created by VictoryWoo
 */
class MissionDetailRepository(private val missionDetailRemoteDataSource: MissionDetailRemoteDataSource) {
    fun getMissionDetailInformation(token: String, categoryId: Int): Single<Response<MissionDetailResponse>> {
        return missionDetailRemoteDataSource.getMissionDetailInformation(token, categoryId)
    }

}