package app.woovictory.forearthforus.model.mission

import com.google.gson.annotations.SerializedName

data class MissionFeedResponse(
    @SerializedName("id")
    var id: Int,
    @SerializedName("mission")
    var mission: Mission,
    @SerializedName("result")
    var result: Int,
    @SerializedName("message")
    var message: String,
    @SerializedName("created")
    var created: String

) {
    data class Category(
        @SerializedName("categoryId")
        var categoryId: Int?,
        @SerializedName("image")
        var image: String,
        @SerializedName("completeMessage")
        var completeMessage: String
    )

    data class Mission(
        @SerializedName("id")
        var id: Int,
        @SerializedName("category")
        var category: Category,
        @SerializedName("image")
        var image: String,
        @SerializedName("title")
        var title: String,
        @SerializedName("comment")
        var comment: String,
        @SerializedName("status")
        var status: String
    )
}