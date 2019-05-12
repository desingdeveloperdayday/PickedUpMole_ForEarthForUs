package app.woovictory.forearthforus.view.main

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.woovictory.forearthforus.model.mission.MissionResponse
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_list_main_mission.view.*

/**
 * Created by VictoryWoo
 */
class MainMissionViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    private val itemImage : ImageView by lazy {
        itemView.itemMainImage
    }

    private val itemTitle : TextView by lazy {
        itemView.itemMainTitle
    }

    private val itemContents : TextView by lazy {
        itemView.itemMainContents
    }

    fun onBind(item: MissionResponse){
        Glide.with(itemView.context).load(item.mainImage).into(itemImage)
        itemTitle.text = item.mainTitle
        itemContents.text = item.mainContents

    }
}