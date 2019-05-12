package app.woovictory.forearthforus.view.mission

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import app.woovictory.forearthforus.R
import app.woovictory.forearthforus.databinding.FragmentMissionBinding
import app.woovictory.forearthforus.model.TodayMissionResponse
import app.woovictory.forearthforus.view.mission.adapter.TodayMissionAdapter
import org.jetbrains.anko.support.v4.startActivity

/**
 * Created by VictoryWoo
 */
class MissionFragment : Fragment() {

    companion object {
        private val ARGUMENT = "MissionFragment"
        private var fragment: MissionFragment? = null

        fun newInstance(): MissionFragment? {
            return MissionFragment()
        }
    }

    private lateinit var fragmentMissionDataBinding: FragmentMissionBinding
    private var todayMissionAdapter: TodayMissionAdapter? = null
        set(value) {
            field = value
            field?.onMissionItemClickListener = { startMissionSelectActivity() }
        }

    private lateinit var itemList: ArrayList<TodayMissionResponse>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentMissionDataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_mission,
            container, false
        )
        return fragmentMissionDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        todayMissionAdapter = TodayMissionAdapter()
        initMockData()
        initRecyclerView()
    }

    private fun initMockData() {
        itemList = ArrayList()
        for (i in 0..5) {
            itemList.add(TodayMissionResponse(R.drawable.fufe_illust_jh_04, "갈 곳 잃은 북극곰을 구해주세요"))
        }
    }

    private fun initRecyclerView() {
        fragmentMissionDataBinding.missionRv.apply {
            layoutManager = LinearLayoutManager(context.applicationContext)
            adapter = todayMissionAdapter
            setHasFixedSize(true)
        }
        todayMissionAdapter?.addItem(itemList)
    }

    private fun startMissionSelectActivity() {
        //toast("떠주세요..ㅜㅜ")
        startActivity<MissionSelectActivity>()
    }

}