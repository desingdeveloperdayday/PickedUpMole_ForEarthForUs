package app.woovictory.forearthforus.api

import app.woovictory.forearthforus.model.account.LoginByEmailRequest
import app.woovictory.forearthforus.model.account.LoginByEmailResponse
import app.woovictory.forearthforus.model.account.SignByEmailRequest
import app.woovictory.forearthforus.model.article.ArticleResponse
import app.woovictory.forearthforus.model.article.DonationDetailResponse
import app.woovictory.forearthforus.model.article.DonationResponse
import app.woovictory.forearthforus.model.category.MissionCategoryResponse
import app.woovictory.forearthforus.model.earth.EarthResponse
import app.woovictory.forearthforus.model.mission.*
import app.woovictory.forearthforus.model.mypage.ScrapResponse
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.*

/**
 * Created by VictoryWoo
 * API 호출하기 위한 추상 메소드 정의.
 */
interface ApiService {

    // 1. email 로그인. - O
    @POST("api/v1/account/login/")
    fun loginByEmail(
        @Body user: LoginByEmailRequest
    ): Single<Response<LoginByEmailResponse>>

    // 2. email 회원가입. - O
    @POST("api/v1/account/registration/")
    fun signUpByEmail(
        @Body signUser: SignByEmailRequest
    ): Single<LoginByEmailResponse>

    // 3. 선호도 선택.
    @POST("api/v1/account/prefer/")
    fun selectPreference(
        @Header("Authorization") Authorization: String,
        @Body preferList: MutableList<Int>
    ): Completable
    //: Maybe<Response<ArrayList<PreferenceModel>>>


    // 4. earth 리스트 main 화면
    @GET("api/v1/earth/{earthLevel}")
    fun getEarthList(
        @Header("Authorization") Authorization: String,
        @Path("earthLevel") earthLevel: Int
    ): Observable<Response<EarthResponse>>


    // 5. category 리스트
    @GET("api/v1/category/")
    fun getCategoryList(
        @Header("Authorization") Authorization: String
    ): Single<Response<ArrayList<MissionCategoryResponse>>>


    // 6. 특정 mission item 의 상세 정보를 가져오는 api
    @GET("api/v1/mission/{id}/")
    fun getMissionDetailInformation(
        @Header("Authorization") Authorization: String,
        @Path("id") id: Int
    ): Single<Response<MissionDetailResponse>>

    // 7. category 에 관련된 mission list 를 가져오는 api
    // ?category={category}/
    @GET("api/v1/mission/")
    fun getMissionCategoryList(
        @Header("Authorization") Authorization: String,
        @Query("category") category: Int
    ): Single<Response<ArrayList<MissionSelectResponse>>>


    // 8. 사용자의 mission list를 얻어오는 api query는 status field에 progress/complete를 넣을 수 있으며
    // , progress는 오늘 진행중인 미션, complete는 완료한 미션을 response로 준다.
    // ?status={progress}/
    @GET("api/v1/feed/")
    fun getUserMissionFeed(
        @Header("Authorization") Authorization: String,
        @Query("status") progress: String
    ): Observable<Response<ArrayList<MissionFeedResponse>>>


    // 9. 사용자가 새로운 미션을 선택하는 api
    // TODO body 나중에 만들고 변경해야함.
    @POST("api/v1/feed/")
    fun selectNewMission(
        @Header("Authorization") Authorization: String,
        @Body mission: MissionSelectRequest
    ): Single<Response<MissionFeedResponse>>

    // 10. 후원처 리스트를 가져오는 api
    @GET("api/v1/donation/")
    fun getDonationList(
        @Header("Authorization") Authorization: String
    ): Single<Response<List<DonationResponse>>>

    // 11. 기부 더보기 리스트를 가져오는 api
    @GET("api/v1/campaign/")
    fun getCampaignList(
        @Header("Authorization") Authorization: String
    ): Single<Response<List<DonationDetailResponse>>>

    // 12.Article 리스트를 가져오는 api
    @GET("api/v1/article/")
    fun getArticleList(
        @Header("Authorization") Authorization: String
    ): Single<Response<List<ArticleResponse>>>

    // 13.
    @PATCH("api/v1/feed/{id}/")
    fun completeMission(
        @Header("Authorization") Authorization: String,
        @Body missionFeedCompleteRequest: MissionFeedCompleteRequest,
        @Path("id") id: String
    ): Single<Response<MissionFeedResponse>>

    // 14. 미션 신청 혹은 완료 시 메인에서 유저 정보를 갱신하기 위한 api
    @GET("api/v1/account/user")
    fun getUserEarthInformation(
        @Header("Authorization") Authorization: String
    ): Observable<Response<EarthResponse>>

    // 15. 스크랩 리스트 불러오기
    @GET("api/v1/scrap/")
    fun getScrapList(
        @Header("Authorization") Authorization: String
    ): Single<Response<ArrayList<ScrapResponse>>>

    // admin - 관리자.


}