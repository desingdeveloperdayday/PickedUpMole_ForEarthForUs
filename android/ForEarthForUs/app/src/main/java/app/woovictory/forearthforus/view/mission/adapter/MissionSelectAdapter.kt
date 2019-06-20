package app.woovictory.forearthforus.view.mission.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.woovictory.forearthforus.databinding.ItemListMissionSelectBinding
import app.woovictory.forearthforus.model.mission.MissionSelectResponse
import app.woovictory.forearthforus.view.mission.viewholder.MissionSelectViewHolder

/**
 * Created by VictoryWoo
 */
class MissionSelectAdapter(private var onMissionSelectItemClickListener: (Int) -> Unit) :
    RecyclerView.Adapter<MissionSelectViewHolder>() {

    private var selectMissionList = ArrayList<MissionSelectResponse>()
    // mission select 화면에서 아이템 클릭 리스너를 달기 위해 함수를 정의한다.
    // 코틀린에서는 함수를 정의해서 변수에 담을 수 있다.
    // Int 를 인자로 받고 아무것도 반환하지 않는다.
    //var onMissionSelectItemClickListener: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MissionSelectViewHolder {
        val binding = ItemListMissionSelectBinding.inflate(
            LayoutInflater.from(parent.context)
            , parent, false
        )
        Log.v("878723", "2")
        return MissionSelectViewHolder(binding, onMissionSelectItemClickListener)
    }

    override fun getItemCount(): Int = selectMissionList.size

    override fun onBindViewHolder(holder: MissionSelectViewHolder, position: Int) {
        holder.apply {
            onBind(selectMissionList[position])
        }
    }

    fun addItem(items: ArrayList<MissionSelectResponse>) {
        selectMissionList.addAll(items)
        notifyDataSetChanged()
    }
}