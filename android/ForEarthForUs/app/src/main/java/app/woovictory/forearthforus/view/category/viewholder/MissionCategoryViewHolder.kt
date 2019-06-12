package app.woovictory.forearthforus.view.category.viewholder

import androidx.recyclerview.widget.RecyclerView
import app.woovictory.forearthforus.databinding.ItemListMissionCategoryBinding
import app.woovictory.forearthforus.model.category.MissionCategoryResponse

/**
 * Created by VictoryWoo
 */
class MissionCategoryViewHolder(
    private val binding: ItemListMissionCategoryBinding,
    private val onClickListener: ((Int) -> Unit)?
) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(item: MissionCategoryResponse) {
        binding.item = item
        binding.executePendingBindings()
        binding.itemMissionLayout.setOnClickListener {
            onClickListener?.invoke(item.categoryId)
        }
    }
}