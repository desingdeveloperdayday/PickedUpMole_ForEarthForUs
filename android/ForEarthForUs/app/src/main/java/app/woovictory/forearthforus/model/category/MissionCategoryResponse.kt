package app.woovictory.forearthforus.model.category

import com.google.gson.annotations.SerializedName

/**
 * Created by VictoryWoo
 */
data class MissionCategoryResponse(
    @SerializedName("categoryId")
    val categoryId: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("completeMessage")
    val completeMessage: String
)