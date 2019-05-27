package app.woovictory.forearthforus.data.source

import app.woovictory.forearthforus.api.ApiService
import app.woovictory.forearthforus.model.login.LoginByEmailResponse
import app.woovictory.forearthforus.model.sign.SignByEmailRequest
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