package app.woovictory.forearthforus.view.mypage.viewholder

import androidx.recyclerview.widget.RecyclerView
import app.woovictory.forearthforus.databinding.ItemListArticleDetailBinding
import app.woovictory.forearthforus.model.article.detail.ArticleDetailResponse

/**
 * Created by VictoryWoo
 */
class ScrapListViewHolder(private val binding: ItemListArticleDetailBinding) : RecyclerView.ViewHolder(binding.root) {


    fun onBind(item: ArticleDetailResponse, position: Int) {
        binding.articleDetailItems = item
    }

}