package app.woovictory.forearthforus.model.earth

import com.google.gson.annotations.SerializedName

/**
 * Created by VictoryWoo
 */
data class EarthResponse(
    @SerializedName("earthLevel")
    val earthLevel: Int,
    @SerializedName("image")
    val image: String = "",
    @SerializedName("content")
    val content: String = ""
)