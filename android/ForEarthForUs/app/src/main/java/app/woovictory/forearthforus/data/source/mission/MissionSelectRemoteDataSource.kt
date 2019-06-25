package app.woovictory.forearthforus.data.source.mission

import app.woovictory.forearthforus.api.ApiService
import app.woovictory.forearthforus.model.mission.MissionFeedResponse
import app.woovictory.forearthforus.model.mission.MissionSelectRequest
import io.reactivex.Single
import retrofit2.Response

/**
 * Created by VictoryWoo
 */
class MissionSelectRemoteDataSource(private val api: ApiService) {

    fun selectNewMission(token: String, mission: MissionSelectRequest): Single<Response<MissionFeedResponse>>{
        return api.selectNewMission("JWT $token", mission)
    }
}