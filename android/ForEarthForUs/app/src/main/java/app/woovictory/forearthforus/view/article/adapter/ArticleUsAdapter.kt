package app.woovictory.forearthforus.view.article.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.woovictory.forearthforus.databinding.ItemListArticleUsBinding
import app.woovictory.forearthforus.model.article.ArticleUsResponse
import app.woovictory.forearthforus.view.article.viewholder.ArticleUsViewHolder

/**
 * Created by VictoryWoo
 */
class ArticleUsAdapter : RecyclerView.Adapter<ArticleUsViewHolder>() {

    private val itemList = ArrayList<ArticleUsResponse>()
    var articleUsItemClickListener: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleUsViewHolder {
        val binding = ItemListArticleUsBinding.inflate(
            LayoutInflater.from(parent.context), parent,
            false
        )
        return ArticleUsViewHolder(binding, articleUsItemClickListener)
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: ArticleUsViewHolder, position: Int) {
        Log.v("9988","size: ${itemList.size}")
        itemList[position].let {
            holder.onBind(it, position)
        }
    }

    fun addItem(items: ArrayList<ArticleUsResponse>) {
        this.itemList.addAll(items)
    }
}