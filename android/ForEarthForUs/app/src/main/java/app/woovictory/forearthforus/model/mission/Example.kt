package app.woovictory.forearthforus.model.mission

import com.google.gson.annotations.SerializedName

data class Example(
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("mission")
    var mission: Mission? = null,
    @SerializedName("result")
    var result: Int? = null,
    @SerializedName("message")
    var message: String? = null,
    @SerializedName("created")
    var created: String? = null

) {
    data class Category(
        @SerializedName("categoryId")
        var categoryId: Int? = null,
        @SerializedName("image")
        var image: String? = null,
        @SerializedName("completeMessage")
        var completeMessage: String? = null

    )


    data class Mission(

        @SerializedName("id")
        var id: Int? = null,
        @SerializedName("category")
        var category: Category? = null,
        @SerializedName("image")
        var image: String? = null,
        @SerializedName("title")
        var title: String? = null,
        @SerializedName("comment")
        var comment: String? = null
    )
}


