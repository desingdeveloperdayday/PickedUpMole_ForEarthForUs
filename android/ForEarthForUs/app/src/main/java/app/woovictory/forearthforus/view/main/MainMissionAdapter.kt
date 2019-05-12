package app.woovictory.forearthforus.view.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.woovictory.forearthforus.R
import app.woovictory.forearthforus.model.mission.MissionResponse

/**
 * Created by VictoryWoo
 */
class MainMissionAdapter(private val itemsMock: ArrayList<MissionResponse>) :
    RecyclerView.Adapter<MainMissionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainMissionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_main_mission, parent, false)
        return MainMissionViewHolder(view)
    }

    override fun getItemCount(): Int = itemsMock.size

    override fun onBindViewHolder(holder: MainMissionViewHolder, position: Int) {
        holder?.apply {
            onBind(itemsMock[position])
        }
    }
}