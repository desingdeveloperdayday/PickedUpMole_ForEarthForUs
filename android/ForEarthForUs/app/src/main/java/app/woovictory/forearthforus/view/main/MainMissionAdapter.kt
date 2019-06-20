package app.woovictory.forearthforus.view.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.woovictory.forearthforus.databinding.ItemListMainMissionBinding
import app.woovictory.forearthforus.model.mission.MissionFeedResponse

/**
 * Created by VictoryWoo
 */
class MainMissionAdapter(private var onMainMissionItemClick: (Int) -> Unit) :
    RecyclerView.Adapter<MainMissionViewHolder>() {

    private var itemsMock = ArrayList<MissionFeedResponse>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainMissionViewHolder {
        val binding = ItemListMainMissionBinding.inflate(
            LayoutInflater.from(parent.context)
            , parent, false
        )
        return MainMissionViewHolder(binding, onMainMissionItemClick)
    }

    override fun getItemCount(): Int = itemsMock.size

    override fun onBindViewHolder(holder: MainMissionViewHolder, position: Int) {
        holder.apply {
            onBind(itemsMock[position])
        }
    }

    fun addItems(items: ArrayList<MissionFeedResponse>) {
        itemsMock.addAll(items)
        notifyDataSetChanged()
    }

}