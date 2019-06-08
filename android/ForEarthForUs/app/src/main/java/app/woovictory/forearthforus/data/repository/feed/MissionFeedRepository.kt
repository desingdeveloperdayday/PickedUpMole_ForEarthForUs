package app.woovictory.forearthforus.data.repository.feed

import app.woovictory.forearthforus.data.source.feed.MissionFeedRemoteDataSource
import app.woovictory.forearthforus.model.mission.Example
import app.woovictory.forearthforus.model.mission.MissionFeedResponse
import io.reactivex.Single
import retrofit2.Response

/**
 * Created by VictoryWoo
 */
class MissionFeedRepository(private val missionFeedRemoteDataSource: MissionFeedRemoteDataSource) {

    fun getUserMissionFeed(token: String, progress: String)
            : Single<Response<ArrayList<Example>>> {
        return missionFeedRemoteDataSource.getUserMissionFeed(token, progress)
    }
}