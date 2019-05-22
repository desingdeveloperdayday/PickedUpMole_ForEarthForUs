package app.woovictory.forearthforus.view.article.viewholder

import androidx.recyclerview.widget.RecyclerView
import app.woovictory.forearthforus.databinding.ItemListArticleUsBinding
import app.woovictory.forearthforus.model.article.ArticleUsResponse

/**
 * Created by VictoryWoo
 */
class ArticleUsViewHolder(
    val binding: ItemListArticleUsBinding,
    val articleUsItemClickListener: ((Int) -> Unit)?
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(item: ArticleUsResponse, position: Int) {
        binding.articleItems = item
        binding.executePendingBindings()

        // image click
        binding.itemArticleEarthImage.setOnClickListener {
            articleUsItemClickListener?.invoke(position)
        }
    }

}