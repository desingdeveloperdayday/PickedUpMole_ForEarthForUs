package app.woovictory.forearthforus.data.repository.user

import app.woovictory.forearthforus.data.source.LoginRemoteDataSource
import app.woovictory.forearthforus.model.login.LoginByEmailResponse
import io.reactivex.Single
import retrofit2.Response

/**
 * Created by VictoryWoo
 */
class LoginRepository(private val loginRemoteDataSource: LoginRemoteDataSource) {
    fun login(email: String, password: String): Single<Response<LoginByEmailResponse>> {
        return loginRemoteDataSource.login(email, password)
    }
}