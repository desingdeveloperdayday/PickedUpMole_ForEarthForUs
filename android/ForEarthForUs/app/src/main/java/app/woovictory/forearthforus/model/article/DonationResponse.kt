package app.woovictory.forearthforus.model.article

import com.google.gson.annotations.SerializedName

/**
 * Created by VictoryWoo
 */
data class DonationResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("link")
    val link: String
)

