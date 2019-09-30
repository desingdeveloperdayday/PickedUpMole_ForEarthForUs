package app.woovictory.forearthforus.view.account

import app.woovictory.forearthforus.model.account.LoginByEmailResponse
import retrofit2.Response

interface LoginContract {
    interface ViewModel{
        fun onSuccessLogin(response: Response<LoginByEmailResponse>)

        fun onFailLogin(throwable: Throwable)
    }
}