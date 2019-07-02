package app.woovictory.forearthforus.model.earth

/**
 * Created by VictoryWoo
 */
data class UpdatedUserInformation(
    val id: Int,
    val email: String,
    val name: String,
    val earthLevel: Int,
    val prefer: Array<String>
)
