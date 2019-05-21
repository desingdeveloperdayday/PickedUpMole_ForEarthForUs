package app.woovictory.forearthforus.view.article.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.woovictory.forearthforus.databinding.ItemListArticleEarthBinding
import app.woovictory.forearthforus.model.article.ArticleEarthResponse
import app.woovictory.forearthforus.view.article.viewholder.ArticleEarthViewHolder

/**
 * Created by VictoryWoo
 */
class ArticleEarthAdapter : RecyclerView.Adapter<ArticleEarthViewHolder>() {

    private var itemList = ArrayList<ArticleEarthResponse>()
    var onArticleEarthItemClickListener: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleEarthViewHolder {

        val binding = ItemListArticleEarthBinding.inflate(
            LayoutInflater.from(parent.context)
            , parent, false
        )
        return ArticleEarthViewHolder(binding, onArticleEarthItemClickListener)
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: ArticleEarthViewHolder, position: Int) {

        itemList[position].let {
            holder.onBind(it, position)
        }
        /*holder.apply {
            onBind(itemList,position)
        }*/
    }

    fun addItem(items: ArrayList<ArticleEarthResponse>) {
        this.itemList.addAll(items)
    }
}