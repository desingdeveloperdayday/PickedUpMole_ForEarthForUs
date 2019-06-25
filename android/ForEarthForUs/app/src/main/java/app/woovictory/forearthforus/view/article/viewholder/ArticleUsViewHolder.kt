package app.woovictory.forearthforus.view.article.viewholder

import androidx.recyclerview.widget.RecyclerView
import app.woovictory.forearthforus.databinding.ItemListArticleUsBinding
import app.woovictory.forearthforus.model.article.ArticleResponse

/**
 * Created by VictoryWoo
 */
class ArticleUsViewHolder(
    val binding: ItemListArticleUsBinding,
    private val articleUsItemClickListener: ((String) -> Unit)?
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(item: ArticleResponse) {
        binding.articleItems = item
        binding.executePendingBindings()

        // image click
        binding.itemArticleEarthImage.setOnClickListener {
            articleUsItemClickListener?.invoke(item.link)
        }
    }

}