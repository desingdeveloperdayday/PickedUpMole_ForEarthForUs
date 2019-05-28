package app.woovictory.forearthforus.model.sign

/**
 * Created by VictoryWoo
 */
data class PreferenceRequest(
    val preferList: Preference
){
    data class Preference(
        val prefer: Int,
        val user: Int
    )
}