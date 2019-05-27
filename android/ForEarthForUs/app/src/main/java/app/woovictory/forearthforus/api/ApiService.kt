package app.woovictory.forearthforus.api

import app.woovictory.forearthforus.model.login.LoginByEmailRequest
import app.woovictory.forearthforus.model.login.LoginByEmailResponse
import app.woovictory.forearthforus.model.sign.SignByEmailRequest
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by VictoryWoo
 * API 호출하기 위한 추상 메소드 정의.
 */
interface ApiService {

    // 1. email 로그인.
    @POST("api/v1/account/login/")
    fun loginByEmail(
        @Body user: LoginByEmailRequest
    ): Single<LoginByEmailResponse>

    // 2. email 회원가입.
    @POST("api/v1/account/registration/")
    fun signUpByEmail(
        @Body signUser: SignByEmailRequest
    ): Single<LoginByEmailResponse>
}