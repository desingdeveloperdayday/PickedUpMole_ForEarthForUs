package app.woovictory.forearthforus.view.main

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import app.woovictory.forearthforus.databinding.ItemListMainMissionBinding
import app.woovictory.forearthforus.model.mission.MissionFeedResponse

/**
 * Created by VictoryWoo
 */
class MainMissionViewHolder(
    private val binding: ItemListMainMissionBinding
    , private val onMainMissionItemClick: (Int, String) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(item: MissionFeedResponse) {
        binding.item = item
        binding.executePendingBindings()

        binding.itemMainImage.setOnClickListener {
            Log.v("2010023 mainMissionholder 1",item.mission.category.categoryId.toString())
            Log.v("2010023 mainMissionholder 2",item.mission.id.toString())
            onMainMissionItemClick.invoke(item.mission.id, item.mission.category.completeMessage)
        }

    }
}