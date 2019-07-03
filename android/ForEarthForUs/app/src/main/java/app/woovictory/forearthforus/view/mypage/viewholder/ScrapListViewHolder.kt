package app.woovictory.forearthforus.view.mypage.viewholder

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import app.woovictory.forearthforus.databinding.ItemListArticleDetailBinding
import app.woovictory.forearthforus.model.article.DonationDetailResponse
import app.woovictory.forearthforus.model.mypage.ScrapResponse

/**
 * Created by VictoryWoo
 */
class ScrapListViewHolder(private val binding: ItemListArticleDetailBinding) : RecyclerView.ViewHolder(binding.root) {


    fun onBind(item: ScrapResponse) {
        Log.v("12351", item.toString())
        binding.articleDetailItems = item.campaign
        binding.itemArticleDetailLikeButton.isSelected = item.campaign.scrap
    }

}