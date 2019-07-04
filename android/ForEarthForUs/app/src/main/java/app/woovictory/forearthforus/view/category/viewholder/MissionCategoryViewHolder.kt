package app.woovictory.forearthforus.view.category.viewholder

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import app.woovictory.forearthforus.databinding.ItemListMissionCategoryBinding
import app.woovictory.forearthforus.model.category.MissionCategoryResponse

/**
 * Created by VictoryWoo
 */
class MissionCategoryViewHolder(
    private val binding: ItemListMissionCategoryBinding,
    private val onClickListener: ((Int, String) -> Unit)?
) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(item: MissionCategoryResponse) {
        binding.item = item
        binding.executePendingBindings()
        binding.itemMissionLayout.setOnClickListener {
            Log.v("878723 categoryAdapter", item.categoryId.toString())
            onClickListener?.invoke(item.categoryId, item.completeMessage)
        }
    }
}