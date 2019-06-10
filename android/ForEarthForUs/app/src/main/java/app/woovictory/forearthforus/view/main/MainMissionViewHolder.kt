package app.woovictory.forearthforus.view.main

import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.woovictory.forearthforus.databinding.ItemListMainMissionBinding
import app.woovictory.forearthforus.model.mission.MissionFeedResponse
import kotlinx.android.synthetic.main.item_list_main_mission.view.*

/**
 * Created by VictoryWoo
 */
class MainMissionViewHolder(private val binding: ItemListMainMissionBinding) : RecyclerView.ViewHolder(binding.root) {

    private val itemImage: ImageView by lazy {
        itemView.itemMainImage
    }

    private val itemTitle: TextView by lazy {
        itemView.itemMainTitle
    }

    private val itemContents: TextView by lazy {
        itemView.itemMainContents
    }

    fun onBind(item: MissionFeedResponse) {
        binding.item = item
        binding.executePendingBindings()

    }
}