package app.woovictory.forearthforus.view.mission.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.woovictory.forearthforus.databinding.ItemListMissionSelectBinding
import app.woovictory.forearthforus.model.mission.MissionSelectResponse
import app.woovictory.forearthforus.view.mission.viewholder.MissionSelectViewHolder

/**
 * Created by VictoryWoo
 */
class MissionSelectAdapter : RecyclerView.Adapter<MissionSelectViewHolder>() {

    private var selectMissionList = ArrayList<MissionSelectResponse>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MissionSelectViewHolder {
        val binding = ItemListMissionSelectBinding.inflate(
            LayoutInflater.from(parent.context)
            , parent, false
        )
        return MissionSelectViewHolder(binding)
    }

    override fun getItemCount(): Int = selectMissionList.size

    override fun onBindViewHolder(holder: MissionSelectViewHolder, position: Int) {
        holder.apply {
            onBind(selectMissionList[position])
        }
    }

    fun addItem(items : ArrayList<MissionSelectResponse>){
        selectMissionList.addAll(items)
        notifyDataSetChanged()
    }
}