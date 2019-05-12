package app.woovictory.forearthforus.view.mission.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.woovictory.forearthforus.databinding.ItemListMissionCategoryBinding
import app.woovictory.forearthforus.model.TodayMissionResponse
import app.woovictory.forearthforus.view.mission.viewholder.TodayMissionViewHolder

/**
 * Created by VictoryWoo
 */
class TodayMissionAdapter : RecyclerView.Adapter<TodayMissionViewHolder>() {

    private val itemList = ArrayList<TodayMissionResponse>()
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

    fun addItem(items: ArrayList<TodayMissionResponse>) {
        itemList.addAll(items)
    }
}