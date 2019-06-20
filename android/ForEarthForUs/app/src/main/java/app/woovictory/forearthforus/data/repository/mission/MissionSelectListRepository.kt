package app.woovictory.forearthforus.data.repository.mission

import app.woovictory.forearthforus.data.source.mission.MissionSelectListRemoteDataSource
import app.woovictory.forearthforus.model.mission.MissionSelectResponse
import io.reactivex.Single
import retrofit2.Response

/**
 * Created by VictoryWoo
 */
class MissionSelectListRepository(
    private val missionSelectListRemoteDataSource
    : MissionSelectListRemoteDataSource
) {

    fun getMissionSelectList(token: String, categoryId: Int)
            : Single<Response<ArrayList<MissionSelectResponse>>> {
        return missionSelectListRemoteDataSource.getMissionSelectList(token, categoryId)
    }
}