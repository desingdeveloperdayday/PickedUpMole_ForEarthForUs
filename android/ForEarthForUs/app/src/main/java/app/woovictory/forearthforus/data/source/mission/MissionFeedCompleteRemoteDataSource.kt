package app.woovictory.forearthforus.data.source.mission

import app.woovictory.forearthforus.api.ApiService
import app.woovictory.forearthforus.model.mission.MissionFeedCompleteRequest
import app.woovictory.forearthforus.model.mission.MissionFeedResponse
import io.reactivex.Single
import retrofit2.Response

/**
 * Created by VictoryWoo
 */
class MissionFeedCompleteRemoteDataSource(private val api: ApiService) {

    fun completeMissionFeed(token: String, missionFeedCompleteRequest: MissionFeedCompleteRequest, id: String)
            : Single<Response<MissionFeedResponse>> {
        return api.completeMission("JWT $token", missionFeedCompleteRequest, id)
    }
}