package app.woovictory.forearthforus.view.category.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.woovictory.forearthforus.databinding.ItemListMissionCategoryBinding
import app.woovictory.forearthforus.model.category.MissionCategoryResponse
import app.woovictory.forearthforus.view.category.viewholder.MissionCategoryViewHolder

/**
 * Created by VictoryWoo
 */
class MissionCategoryAdapter : RecyclerView.Adapter<MissionCategoryViewHolder>() {

    private val itemList = ArrayList<MissionCategoryResponse>()
    var onMissionItemClickListener: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MissionCategoryViewHolder {
        val binding = ItemListMissionCategoryBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return MissionCategoryViewHolder(
            binding,
            onMissionItemClickListener
        )
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holderCategory: MissionCategoryViewHolder, position: Int) {
        holderCategory.apply {
            onBind(itemList[position])
        }
    }

    fun addItem(items: ArrayList<MissionCategoryResponse>) {
        if(this.itemList.size == 0){
            this.itemList.addAll(items)
            notifyDataSetChanged()
        }else{
            Log.v("970822", itemList.size.toString())
        }
    }
}