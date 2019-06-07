package app.woovictory.forearthforus.model.account

/**
 * Created by VictoryWoo
 */
data class LoginByEmailRequest(
    val email : String = "",
    val password : String = ""
)