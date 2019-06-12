package app.woovictory.forearthforus.data.repository.mission

import app.woovictory.forearthforus.data.source.mission.MissionSelectRemoteDataSource
import app.woovictory.forearthforus.model.mission.MissionSelectResponsee
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
            : Single<Response<ArrayList<MissionSelectResponsee>>> {
        return missionSelectRemoteDataSource.getMissionSelectList(token, categoryId)
    }
}