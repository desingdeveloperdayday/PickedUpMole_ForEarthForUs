package app.woovictory.forearthforus.data.repository.mission

import app.woovictory.forearthforus.data.source.mission.MissionSelectRemoteDataSource
import app.woovictory.forearthforus.model.mission.MissionFeedResponse
import app.woovictory.forearthforus.model.mission.MissionSelectRequest
import io.reactivex.Single
import retrofit2.Response

/**
 * Created by VictoryWoo
 */
class MissionSelectRepository(private val missionSelectRemoteDataSource: MissionSelectRemoteDataSource) {

    fun selectNewMission(token: String, mission: MissionSelectRequest): Single<Response<MissionFeedResponse>>{
        return missionSelectRemoteDataSource.selectNewMission(token, mission)
    }
}