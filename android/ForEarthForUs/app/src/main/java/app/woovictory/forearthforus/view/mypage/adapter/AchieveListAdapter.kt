package app.woovictory.forearthforus.view.mypage.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.woovictory.forearthforus.databinding.ItemListAchieveMissionBinding
import app.woovictory.forearthforus.model.mypage.AchieveResponseMock
import app.woovictory.forearthforus.view.mypage.viewholder.AchieveListViewHolder

/**
 * Created by VictoryWoo
 */
class AchieveListAdapter : RecyclerView.Adapter<AchieveListViewHolder>() {

    private var itemList = ArrayList<AchieveResponseMock>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AchieveListViewHolder {
        val binding = ItemListAchieveMissionBinding.inflate(
            LayoutInflater.from(parent.context)
            , parent, false
        )
        return AchieveListViewHolder(binding)
    }

    override fun getItemCount() = itemList.size

    override fun onBindViewHolder(holder: AchieveListViewHolder, position: Int) {
        holder.apply {
            onBind(itemList[position])
        }
    }

    fun addAllItem(items: ArrayList<AchieveResponseMock>) {
        itemList.addAll(items)
    }
}