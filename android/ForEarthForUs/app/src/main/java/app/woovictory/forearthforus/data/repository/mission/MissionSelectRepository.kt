package app.woovictory.forearthforus.data.repository.mission

import app.woovictory.forearthforus.data.source.mission.MissionSelectRemoteDataSource
import app.woovictory.forearthforus.model.mission.MissionSelectResponse
import io.reactivex.Single
import retrofit2.Response

/**
 * Created by VictoryWoo
 */
class MissionSelectRepository(
    private val missionSelectRemoteDataSource
    : MissionSelectRemoteDataSource
) {

    fun getMissionSelectList(token: String, categoryId: Int)
            : Single<Response<ArrayList<MissionSelectResponse>>> {
        return missionSelectRemoteDataSource.getMissionSelectList(token, categoryId)
    }
}