package app.woovictory.forearthforus.data.repository

import app.woovictory.forearthforus.data.source.LoginRemoteDataSource
import app.woovictory.forearthforus.model.login.LoginByEmailResponse
import io.reactivex.Single

/**
 * Created by VictoryWoo
 */
class LoginRepository(private val loginRemoteDataSource: LoginRemoteDataSource) {
    fun login(email: String, password: String): Single<LoginByEmailResponse> {
        return loginRemoteDataSource.login(email, password)
    }
}