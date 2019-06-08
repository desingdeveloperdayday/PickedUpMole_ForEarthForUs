package app.woovictory.forearthforus.view.mission.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.woovictory.forearthforus.databinding.ItemListMissionCategoryBinding
import app.woovictory.forearthforus.model.category.MissionCategoryResponse
import app.woovictory.forearthforus.view.mission.viewholder.TodayMissionViewHolder

/**
 * Created by VictoryWoo
 */
class TodayMissionAdapter : RecyclerView.Adapter<TodayMissionViewHolder>() {

    private val itemList = ArrayList<MissionCategoryResponse>()
    var onMissionItemClickListener: (() -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodayMissionViewHolder {
        val binding = ItemListMissionCategoryBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return TodayMissionViewHolder(
            binding,
            onMissionItemClickListener
        )
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: TodayMissionViewHolder, position: Int) {
        holder.apply {
            onBind(itemList[position])
        }
    }

    fun addItem(items: ArrayList<MissionCategoryResponse>) {
        this.itemList.addAll(items)
        notifyDataSetChanged()
        Log.v("970822", itemList.size.toString())
        //Log.v("970822",itemList[0].image)
    }
}