package app.woovictory.forearthforus.view.article.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.woovictory.forearthforus.databinding.ItemListArticleDetailBinding
import app.woovictory.forearthforus.model.article.ArticleDetailResponse
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
        return ArticleDetailViewHolder(binding, articleDetailImageClickListener, articleDetailLikeClickListener)
    }

    override fun getItemCount(): Int {
        Log.v("0099 itemList size", itemList.size.toString())
        return itemList.size
    }

    override fun onBindViewHolder(holder: ArticleDetailViewHolder, position: Int) {
        /*itemList[position].let { item ->
            holder.onBind(item, position)
        }
        */
        holder.apply {
            Log.v("0099 bindViewHolder", itemList.size.toString())
            onBind(itemList[position],position)
        }
    }

    fun addItem(items: List<ArticleDetailResponse>) {
        this.itemList.addAll(items)
        Log.v("0099 adapter", itemList.size.toString())
    }

}