package app.woovictory.forearthforus.view.article.viewholder

import androidx.recyclerview.widget.RecyclerView
import app.woovictory.forearthforus.databinding.ItemListArticleDonationBinding
import app.woovictory.forearthforus.model.article.DonationResponse

/**
 * Created by VictoryWoo
 */
class ArticleEarthViewHolder(
    private val binding: ItemListArticleDonationBinding,
    private val onArticleEarthItemClickListener: ((String) -> Unit)?
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(item: DonationResponse, position: Int) {
        binding.donationItem = item
        binding.executePendingBindings()
        // item 클릭 리스너 달아줌.
        binding.itemArticleEarthImage.setOnClickListener {
            onArticleEarthItemClickListener?.invoke(item.link)
        }
    }
}