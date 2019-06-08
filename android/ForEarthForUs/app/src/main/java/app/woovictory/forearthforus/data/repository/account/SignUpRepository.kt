package app.woovictory.forearthforus.data.repository.account

import app.woovictory.forearthforus.data.Repository
import app.woovictory.forearthforus.data.source.account.SignUpRemoteDataSource
import app.woovictory.forearthforus.model.account.LoginByEmailResponse
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