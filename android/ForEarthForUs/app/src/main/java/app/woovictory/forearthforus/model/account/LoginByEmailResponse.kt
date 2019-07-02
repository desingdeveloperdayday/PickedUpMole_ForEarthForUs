package app.woovictory.forearthforus.model.account

import com.google.gson.annotations.SerializedName

/**
 * Created by VictoryWoo
 */
data class LoginByEmailResponse(
    @SerializedName("token")
    val token: String,
    @SerializedName("user")
    val user: User
) {
    data class User(
        @SerializedName("id")
        val id: Int,
        @SerializedName("email")
        val email: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("earthLevel")
        val earthLevel: Int,
        @SerializedName("prefer")
        val prefer: Array<String>
    )
}