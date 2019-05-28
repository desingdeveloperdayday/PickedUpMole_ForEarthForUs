package app.woovictory.forearthforus.model.sign

/**
 * Created by VictoryWoo
 */
data class PreferenceResponse(
    val earth: Earth
){
    data class Earth(
        val content: String,
        val image: String,
        val earthLevel: Int
    )
}