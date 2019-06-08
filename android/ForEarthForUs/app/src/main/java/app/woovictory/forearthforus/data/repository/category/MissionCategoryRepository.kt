package app.woovictory.forearthforus.data.repository.category

import app.woovictory.forearthforus.data.source.category.MissionCategoryRemoteDataSource
import app.woovictory.forearthforus.model.category.MissionCategoryResponse
import io.reactivex.Single
import retrofit2.Response

/**
 * Created by VictoryWoo
 */
class MissionCategoryRepository(private val missionCategoryRemoteDataSource: MissionCategoryRemoteDataSource) {

    fun getMissionCategoryList(token: String): Single<Response<ArrayList<MissionCategoryResponse>>> {
        return missionCategoryRemoteDataSource.getMissionCategoryList(token)
    }
}