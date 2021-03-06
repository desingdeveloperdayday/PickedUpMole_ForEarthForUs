package app.woovictory.forearthforus.model.mission

import com.google.gson.annotations.SerializedName

/**
 * Created by VictoryWoo
 */
data class MissionSelectResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("category")
    val category: Int,
    @SerializedName("image")
    var image: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("content")
    val content: String,
    @SerializedName("status")
    var status: String
)