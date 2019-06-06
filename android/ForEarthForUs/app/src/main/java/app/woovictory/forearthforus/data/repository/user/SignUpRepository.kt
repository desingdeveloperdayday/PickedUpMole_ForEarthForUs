package app.woovictory.forearthforus.data.repository.user

import app.woovictory.forearthforus.data.Repository
import app.woovictory.forearthforus.data.source.SignUpRemoteDataSource
import app.woovictory.forearthforus.model.login.LoginByEmailResponse
import io.reactivex.Single

/**
 * Created by VictoryWoo
 */
class SignUpRepository(private val signUpRemoteDataSource: SignUpRemoteDataSource) : Repository {
    fun signUp(email: String, name: String, password1: String, password2: String)
            : Single<LoginByEmailResponse> {
        return signUpRemoteDataSource.signUp(email, name, password1, password2)
    }
}