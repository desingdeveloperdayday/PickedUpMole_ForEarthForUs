package app.woovictory.forearthforus.view.article.viewholder

import androidx.recyclerview.widget.RecyclerView
import app.woovictory.forearthforus.databinding.ItemListArticleEarthBinding
import app.woovictory.forearthforus.model.article.ArticleEarthResponse

/**
 * Created by VictoryWoo
 */
class ArticleEarthViewHolder(
    private val binding: ItemListArticleEarthBinding,
    private val onArticleEarthItemClickListener: ((Int) -> Unit)?
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(item: ArticleEarthResponse, position: Int) {
        binding.itemEarth = item
        binding.executePendingBindings()
        // item 클릭 리스너 달아줌.
        binding.itemArticleEarthImage.setOnClickListener {
            onArticleEarthItemClickListener?.invoke(position)
        }
    }
}