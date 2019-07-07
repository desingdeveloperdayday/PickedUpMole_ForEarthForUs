package app.woovictory.forearthforus.view.article.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.woovictory.forearthforus.databinding.ItemListArticleDetailBinding
import app.woovictory.forearthforus.model.article.DonationDetailResponse
import app.woovictory.forearthforus.view.article.viewholder.ArticleDetailViewHolder

/**
 * Created by VictoryWoo
 */
class ArticleDetailAdapter : RecyclerView.Adapter<ArticleDetailViewHolder>() {

    private var itemList = ArrayList<DonationDetailResponse>()
    var articleDetailLikeClickListener: ((Int, Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleDetailViewHolder {
        val binding = ItemListArticleDetailBinding.inflate(
            LayoutInflater.from(parent.context)
            , parent, false
        )
        return ArticleDetailViewHolder(binding, articleDetailLikeClickListener)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ArticleDetailViewHolder, position: Int) {
        holder.apply {
            onBind(itemList[position])
        }
    }

    fun addItem(items: List<DonationDetailResponse>) {
        this.itemList.addAll(items)
    }
}