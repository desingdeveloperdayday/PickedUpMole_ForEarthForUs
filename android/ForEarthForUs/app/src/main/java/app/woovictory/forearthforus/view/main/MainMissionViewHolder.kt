package app.woovictory.forearthforus.view.main

import androidx.recyclerview.widget.RecyclerView
import app.woovictory.forearthforus.databinding.ItemListMainMissionBinding
import app.woovictory.forearthforus.model.mission.MissionFeedResponse

/**
 * Created by VictoryWoo
 */
class MainMissionViewHolder(
    private val binding: ItemListMainMissionBinding
    , private val onMainMissionItemClick: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(item: MissionFeedResponse) {
        binding.item = item
        binding.executePendingBindings()

        binding.itemMainImage.setOnClickListener {
            onMainMissionItemClick.invoke(item.mission.id)
        }

    }
}