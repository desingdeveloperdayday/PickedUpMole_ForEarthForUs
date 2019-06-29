package app.woovictory.forearthforus.data.repository.feed

import app.woovictory.forearthforus.data.source.feed.MissionFeedRemoteDataSource
import app.woovictory.forearthforus.model.mission.MissionFeedResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Response

/**
 * Created by VictoryWoo
 */
class MissionFeedRepository(private val missionFeedRemoteDataSource: MissionFeedRemoteDataSource) {

    fun getUserMissionFeed(token: String, progress: String)
            : Observable<Response<ArrayList<MissionFeedResponse>>> {
        return missionFeedRemoteDataSource.getUserMissionFeed(token, progress)
    }
}