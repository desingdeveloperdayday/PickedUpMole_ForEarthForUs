package app.woovictory.forearthforus.model.article

import com.google.gson.annotations.SerializedName

/**
 * Created by VictoryWoo
 */
data class DonationDetailResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String = "",
    @SerializedName("subTitle")
    val subTitle: String = "",
    @SerializedName("image")
    val image: String = "",
    @SerializedName("link")
    val link: String = "",
    @SerializedName("scrap")
    val scrap: Boolean
)
