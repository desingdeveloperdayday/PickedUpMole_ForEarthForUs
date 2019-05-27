package app.woovictory.forearthforus.model.login

/**
 * Created by VictoryWoo
 */
data class LoginByEmailResponse(
    val token : String,
    val user : User?
){
    data class User(
        val id: Int,
        val email : String,
        val name : String,
        val earthLevel: Int
    )
}

