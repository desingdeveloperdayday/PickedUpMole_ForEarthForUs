package app.woovictory.forearthforus.model.article

import com.google.gson.annotations.SerializedName

/**
 * Created by VictoryWoo
 * DonationDetailResponse 랑 같음.
 */
data class ArticleResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String = "",
    @SerializedName("subTitle")
    val subTitle: String = "",
    @SerializedName("image")
    val image: String = "",
    @SerializedName("link")
    val link: String = ""
)