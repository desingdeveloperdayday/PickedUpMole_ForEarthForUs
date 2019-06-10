package app.woovictory.forearthforus.data.source.feed

import app.woovictory.forearthforus.api.ApiService
import app.woovictory.forearthforus.model.mission.MissionFeedResponse
import io.reactivex.Single
import retrofit2.Response

/**
 * Created by VictoryWoo
 */
class MissionFeedRemoteDataSource(val api: ApiService) {

    fun getUserMissionFeed(token: String, progress: String)
            : Single<Response<ArrayList<MissionFeedResponse>>> {
        return api.getUserMissionFeed("JWT $token", progress)
    }
}