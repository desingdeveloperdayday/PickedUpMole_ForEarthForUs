package app.woovictory.forearthforus.view.mission.viewholder

import android.util.Log
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import app.woovictory.forearthforus.databinding.ItemListMissionSelectBinding
import app.woovictory.forearthforus.model.mission.MissionSelectResponse

/**
 * Created by VictoryWoo
 */
class MissionSelectViewHolder(
    private val binding: ItemListMissionSelectBinding
    , private val missionSelectItemClickListener: ((Int, ImageView, String) -> Unit)?
) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(item: MissionSelectResponse) {
        binding.selectItem = item
        binding.itemMissionSelectImage.apply {
            transitionName = item.image
        }
        binding.executePendingBindings()
        binding.itemMissionSelect.setOnClickListener {
            // invoke() 함수는 정의한 missionSelectItemClickListener 함수를 호출하는 역할이다.
            //Log.v("878723","3")
            missionSelectItemClickListener?.invoke(item.id, binding.itemMissionSelectImage, item.image)
            Log.v("878723", item.id.toString())
        }

    }
}