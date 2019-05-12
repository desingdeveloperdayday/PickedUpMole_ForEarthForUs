package app.woovictory.forearthforus.view.mission.viewholder

import androidx.recyclerview.widget.RecyclerView
import app.woovictory.forearthforus.databinding.ItemListMissionSelectBinding
import app.woovictory.forearthforus.model.mission.MissionSelectResponse

/**
 * Created by VictoryWoo
 */
class MissionSelectViewHolder(private val binding: ItemListMissionSelectBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(item : MissionSelectResponse){
        binding.selectItem = item
        binding.executePendingBindings()
    }
}