package app.woovictory.forearthforus.view.article.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.woovictory.forearthforus.databinding.ItemListArticleDonationBinding
import app.woovictory.forearthforus.model.article.DonationResponse
import app.woovictory.forearthforus.view.article.viewholder.ArticleEarthViewHolder

/**
 * Created by VictoryWoo
 */
class ArticleEarthAdapter : RecyclerView.Adapter<ArticleEarthViewHolder>() {

    private var itemList = ArrayList<DonationResponse>()
    var onArticleEarthItemClickListener: ((String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleEarthViewHolder {

        val binding = ItemListArticleDonationBinding.inflate(
            LayoutInflater.from(parent.context)
            , parent, false
        )
        return ArticleEarthViewHolder(binding, onArticleEarthItemClickListener)
    }

    override fun getItemCount(): Int {
        Log.v("9929d size",itemList.size.toString())
        return itemList.size
    }

    override fun onBindViewHolder(holder: ArticleEarthViewHolder, position: Int) {

        itemList[position].let {
            holder.onBind(it, position)
        }
    }

    fun addItem(items: List<DonationResponse>) {
        this.itemList.addAll(items)
        Log.v("9929 addItem",itemList.size.toString())
    }
}