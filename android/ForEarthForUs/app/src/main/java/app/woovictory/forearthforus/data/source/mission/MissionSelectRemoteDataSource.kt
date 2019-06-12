package app.woovictory.forearthforus.data.source.mission

import app.woovictory.forearthforus.api.ApiService
import app.woovictory.forearthforus.model.mission.MissionSelectResponsee
import io.reactivex.Single
import retrofit2.Response

/**
 * Created by VictoryWoo
 */
class MissionSelectRemoteDataSource(private val api: ApiService) {

    fun getMissionSelectList(token: String, categoryId: Int)
            : Single<Response<ArrayList<MissionSelectResponsee>>> {
        return api.getMissionCategoryList("JWT $token", categoryId)
    }
}