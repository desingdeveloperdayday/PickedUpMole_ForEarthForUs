package app.woovictory.forearthforus.view.mission.viewholder

import androidx.recyclerview.widget.RecyclerView
import app.woovictory.forearthforus.databinding.ItemListMissionCategoryBinding
import app.woovictory.forearthforus.model.category.MissionCategoryResponse

/**
 * Created by VictoryWoo
 */
class TodayMissionViewHolder(
    private val binding: ItemListMissionCategoryBinding,
    private val onClickListener: (() -> Unit)?
) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(item: MissionCategoryResponse) {
        binding.item = item
        binding.executePendingBindings()
        binding.itemMissionLayout.setOnClickListener {
            onClickListener?.invoke()
        }
    }
}