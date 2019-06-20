package app.woovictory.forearthforus.data.source.mission

import app.woovictory.forearthforus.api.ApiService
import app.woovictory.forearthforus.model.mission.MissionDetailResponse
import io.reactivex.Single
import retrofit2.Response

class MissionDetailRemoteDataSource(private val api: ApiService) {

    fun getMissionDetailInformation(token: String, categoryId: Int)
            : Single<Response<MissionDetailResponse>> {
        return api.getMissionDetailInformation("JWT $token", categoryId)
    }
}
