package app.woovictory.forearthforus.data.source.category

import app.woovictory.forearthforus.api.ApiService
import app.woovictory.forearthforus.model.category.MissionCategoryResponse
import io.reactivex.Single
import retrofit2.Response

/**
 * Created by VictoryWoo
 */
class MissionCategoryRemoteDataSource(private val api:ApiService) {

    fun getMissionCategoryList(token: String): Single<Response<ArrayList<MissionCategoryResponse>>>{
        return api.getCategoryList("JWT $token")
    }
}