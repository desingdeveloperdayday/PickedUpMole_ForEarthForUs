package app.woovictory.forearthforus.data.source.account

import app.woovictory.forearthforus.api.ApiService
import app.woovictory.forearthforus.model.account.LoginByEmailResponse
import app.woovictory.forearthforus.model.account.SignByEmailRequest
import io.reactivex.Single

/**
 * Created by VictoryWoo
 */
class SignUpRemoteDataSource(val api: ApiService) {
    fun signUp(email: String, name: String, password1: String, password2: String)
            : Single<LoginByEmailResponse> {
        return api.signUpByEmail(SignByEmailRequest(email, name, password1, password2))
    }

}