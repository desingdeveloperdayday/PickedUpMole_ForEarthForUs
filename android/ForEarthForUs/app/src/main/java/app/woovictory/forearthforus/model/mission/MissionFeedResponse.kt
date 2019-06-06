package app.woovictory.forearthforus.model.mission

import com.google.gson.annotations.SerializedName

/**
 * Created by VictoryWoo
 */
data class MissionFeedResponse(
    val missionFeed: ArrayList<MissionFeed>
) {
    data class MissionFeed(
        @SerializedName("id")
        val id: Int,
        @SerializedName("mission")
        val mission: Mission,
        @SerializedName("result")
        val result: Int,
        @SerializedName("message")
        val message: String,
        @SerializedName("created")
        val created: String
    )

    data class Mission(
        @SerializedName("id")
        val id: Int,
        @SerializedName("category")
        val category: Category,
        @SerializedName("image")
        val image: String,
        @SerializedName("title")
        val title: String,
        @SerializedName("comment")
        val comment: String
    )

    data class Category(
        @SerializedName("categoryId")
        val categoryId: Int,
        @SerializedName("image")
        val image: String,
        @SerializedName("completeMessage")
        val completeMessage: String
    )
}
