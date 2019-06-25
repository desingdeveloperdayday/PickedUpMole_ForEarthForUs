package app.woovictory.forearthforus.view.article.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.woovictory.forearthforus.databinding.ItemListArticleUsBinding
import app.woovictory.forearthforus.model.article.ArticleResponse
import app.woovictory.forearthforus.view.article.viewholder.ArticleUsViewHolder

/**
 * Created by VictoryWoo
 */
class ArticleListAdapter : RecyclerView.Adapter<ArticleUsViewHolder>() {

    private val itemList = ArrayList<ArticleResponse>()
    var articleUsItemClickListener: ((String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleUsViewHolder {
        val binding = ItemListArticleUsBinding.inflate(
            LayoutInflater.from(parent.context), parent,
            false
        )
        return ArticleUsViewHolder(binding, articleUsItemClickListener)
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: ArticleUsViewHolder, position: Int) {
        itemList[position].let {
            holder.onBind(it)
        }
    }

    fun addItem(items: List<ArticleResponse>) {
        this.itemList.addAll(items)
    }
}