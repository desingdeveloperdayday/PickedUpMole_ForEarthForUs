package app.woovictory.forearthforus.data.source.account

import app.woovictory.forearthforus.api.ApiService
import app.woovictory.forearthforus.model.account.LoginByEmailRequest
import app.woovictory.forearthforus.model.account.LoginByEmailResponse
import io.reactivex.Single
import retrofit2.Response

/**
 * Created by VictoryWoo
 * DataSource 라는 식으로 인터페이스로 빼놓고 Remote, local DataSource 에서
 * 구현해서 사용하는 식으로 쓴다.
 * 그리고 Repository 가 이 객체를 가지고 구현해서
 * ViewModel 로 넘긴다.
 */
class LoginRemoteDataSource(val api: ApiService) {
    fun login(email: String, password: String): Single<Response<LoginByEmailResponse>> {
        return api.loginByEmail(LoginByEmailRequest(email, password))
    }
}