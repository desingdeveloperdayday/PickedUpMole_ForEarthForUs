package app.woovictory.forearthforus.model.mypage

import app.woovictory.forearthforus.model.article.DonationDetailResponse
import com.google.gson.annotations.SerializedName

/**
 * Created by VictoryWoo
 */
data class ScrapResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("kind")
    val kind: Int,
    @SerializedName("campaign")
    val campaign: DonationDetailResponse,
    @SerializedName("article")
    val article: String?
)