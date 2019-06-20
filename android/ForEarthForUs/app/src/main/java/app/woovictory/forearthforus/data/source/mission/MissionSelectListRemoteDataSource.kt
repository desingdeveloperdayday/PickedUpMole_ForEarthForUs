package app.woovictory.forearthforus.data.source.mission

import app.woovictory.forearthforus.api.ApiService
import app.woovictory.forearthforus.model.mission.MissionSelectResponse
import io.reactivex.Single
import retrofit2.Response

/**
 * Created by VictoryWoo
 */
class MissionSelectListRemoteDataSource(private val api: ApiService) {

    fun getMissionSelectList(token: String, categoryId: Int)
            : Single<Response<ArrayList<MissionSelectResponse>>> {
        return api.getMissionCategoryList("JWT $token", categoryId)
    }
}