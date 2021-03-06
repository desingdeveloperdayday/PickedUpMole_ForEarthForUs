package app.woovictory.forearthforus.view.mypage.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.woovictory.forearthforus.databinding.ItemListArticleDetailBinding
import app.woovictory.forearthforus.model.mypage.ScrapResponse
import app.woovictory.forearthforus.view.mypage.viewholder.ScrapListViewHolder

/**
 * Created by VictoryWoo
 */
class ScrapListAdapter : RecyclerView.Adapter<ScrapListViewHolder>() {

    private val itemList = ArrayList<ScrapResponse>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScrapListViewHolder {
        val binding = ItemListArticleDetailBinding.inflate(
            LayoutInflater.from(parent.context)
            , parent, false
        )
        return ScrapListViewHolder(binding)
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: ScrapListViewHolder, position: Int) {
        holder.apply {
            onBind(itemList[position])
        }
    }

    fun addItem(items: ArrayList<ScrapResponse>) {
        itemList.addAll(items)
    }

}