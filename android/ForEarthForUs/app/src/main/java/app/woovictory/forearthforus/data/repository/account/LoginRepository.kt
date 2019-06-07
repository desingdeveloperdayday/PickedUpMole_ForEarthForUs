package app.woovictory.forearthforus.data.repository.account

import app.woovictory.forearthforus.data.source.account.LoginRemoteDataSource
import app.woovictory.forearthforus.model.account.LoginByEmailResponse
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