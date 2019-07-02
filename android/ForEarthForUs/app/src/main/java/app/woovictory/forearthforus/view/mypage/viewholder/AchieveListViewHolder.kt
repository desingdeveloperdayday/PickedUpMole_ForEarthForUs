package app.woovictory.forearthforus.view.mypage.viewholder

import androidx.recyclerview.widget.RecyclerView
import app.woovictory.forearthforus.databinding.ItemListAchieveMissionBinding
import app.woovictory.forearthforus.model.mission.MissionFeedResponse

/**
 * Created by VictoryWoo
 */
class AchieveListViewHolder(val binding: ItemListAchieveMissionBinding) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(item: MissionFeedResponse) {
        binding.achieveListItem = item
    }
}