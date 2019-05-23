package app.woovictory.forearthforus.view.article.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.woovictory.forearthforus.databinding.ItemListArticleDetailBinding
import app.woovictory.forearthforus.model.article.detail.ArticleDetailResponse
import app.woovictory.forearthforus.view.article.viewholder.ArticleDetailViewHolder

/**
 * Created by VictoryWoo
 */
class ArticleDetailAdapter : RecyclerView.Adapter<ArticleDetailViewHolder>() {

    private var itemList = ArrayList<ArticleDetailResponse>()
    var articleDetailImageClickListener: ((Int) -> Unit)? = null
    var articleDetailLikeClickListener: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleDetailViewHolder {
        val binding = ItemListArticleDetailBinding.inflate(
            LayoutInflater.from(parent.context)
            , parent, false
        )
        return ArticleDetailViewHolder(binding,articleDetailImageClickListener,articleDetailLikeClickListener)
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: ArticleDetailViewHolder, position: Int) {
        itemList[position].let { item ->
            holder.onBind(item, position)
        }
    }

    fun addItem(items: ArrayList<ArticleDetailResponse>) {
        this.itemList.addAll(items)
    }

}