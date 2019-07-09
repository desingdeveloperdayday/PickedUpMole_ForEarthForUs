package app.woovictory.forearthforus.view.mypage.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.woovictory.forearthforus.databinding.ItemListAchieveMissionBinding
import app.woovictory.forearthforus.model.mission.MissionFeedResponse
import app.woovictory.forearthforus.view.mypage.viewholder.AchieveListViewHolder

/**
 * Created by VictoryWoo
 */
class AchieveListAdapter : RecyclerView.Adapter<AchieveListViewHolder>() {

    private var itemList = mutableListOf<MissionFeedResponse>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AchieveListViewHolder {
        val binding = ItemListAchieveMissionBinding.inflate(
            LayoutInflater.from(parent.context)
            , parent, false
        )
        return AchieveListViewHolder(binding)
    }

    override fun getItemCount() = itemList.size

    override fun onBindViewHolder(holder: AchieveListViewHolder, position: Int) {
        holder.apply {
            onBind(itemList[position])
        }
    }

    fun addAllItem(items: List<MissionFeedResponse>) {
        itemList.clear()
        itemList.addAll(items)
    }
}