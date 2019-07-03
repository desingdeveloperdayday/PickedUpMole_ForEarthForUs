package app.woovictory.forearthforus.view.article.viewholder

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import app.woovictory.forearthforus.databinding.ItemListArticleDetailBinding
import app.woovictory.forearthforus.model.article.DonationDetailResponse

/**
 * Created by VictoryWoo
 */
class ArticleDetailViewHolder(
    private val binding: ItemListArticleDetailBinding,
    private val articleDetailImageClickListener: ((Int) -> Unit)?,
    private val articleDetailLikeClickListener: ((Int) -> Unit)?
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(item: DonationDetailResponse) {
        Log.v("06606", item.toString())
        binding.articleDetailItems = item
        binding.executePendingBindings()

        // image click
        binding.itemArticleDetailImage.setOnClickListener {
            articleDetailImageClickListener?.invoke(item.id)
        }

        // like click
        binding.itemArticleDetailLikeButton.setOnClickListener {
            binding.itemArticleDetailLikeButton.isSelected = !binding.itemArticleDetailLikeButton.isSelected
            articleDetailLikeClickListener?.invoke(item.id)
        }

        binding.itemArticleDetailLikeButton.isSelected = item.scrap
    }
}