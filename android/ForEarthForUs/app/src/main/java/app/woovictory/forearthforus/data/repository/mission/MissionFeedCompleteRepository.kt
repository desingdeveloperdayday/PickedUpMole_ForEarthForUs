package app.woovictory.forearthforus.data.repository.mission

import app.woovictory.forearthforus.data.source.mission.MissionFeedCompleteRemoteDataSource
import app.woovictory.forearthforus.model.mission.MissionFeedCompleteRequest
import app.woovictory.forearthforus.model.mission.MissionFeedResponse
import io.reactivex.Single
import retrofit2.Response

/**
 * Created by VictoryWoo
 */
class MissionFeedCompleteRepository(
    private val missionFeedCompleteRemoteDataSource
    : MissionFeedCompleteRemoteDataSource
) {

    fun completeMissionFeed(token: String, missionFeedCompleteRequest: MissionFeedCompleteRequest, id: Int)
            : Single<Response<MissionFeedResponse>> {
        return missionFeedCompleteRemoteDataSource.completeMissionFeed(token, missionFeedCompleteRequest, id)
    }
}