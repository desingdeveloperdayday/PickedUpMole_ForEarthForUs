package app.woovictory.forearthforus.model.mission

import com.google.gson.annotations.SerializedName

/**
 * Created by VictoryWoo
 */
data class MissionDetailResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("category")
    val category: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("content")
    val content: String,
    @SerializedName("missionTipTitle")
    val missionTipTitle: String,
    @SerializedName("missionTipContent")
    val missionTipContent: String,
    @SerializedName("missionMethodContent")
    val missionMethodContent: String,
    @SerializedName("missionEffectContent")
    val missionEffectContent: String,
    @SerializedName("status")
    var status: String
)