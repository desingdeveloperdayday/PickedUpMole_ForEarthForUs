package app.woovictory.forearthforus.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import app.woovictory.forearthforus.R
import app.woovictory.forearthforus.databinding.FragmentMainBinding
import app.woovictory.forearthforus.model.mission.MissionResponse

/**
 * Created by VictoryWoo
 */
class MainFragment : Fragment() {

    companion object {
        fun newInstance(): MainFragment? {
            return MainFragment()
        }
    }

    private lateinit var fragmentMainBinding: FragmentMainBinding
    private lateinit var itemList: ArrayList<MissionResponse>
    private lateinit var mainMissionAdapter: MainMissionAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentMainBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        return fragmentMainBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initMockData()
        initRecyclerView()
    }

    // mock 데이터 생성.
    private fun initMockData() {
        itemList = ArrayList()

        for (i in 0..5) {
            itemList.add(
                MissionResponse(
                    R.drawable.fufe_illust_jh_04,
                    "지구 재활용",
                    "재활용 하자."
                )
            )
        }
        mainMissionAdapter = MainMissionAdapter(itemList)
    }

    private fun initRecyclerView() {
        fragmentMainBinding.mainRv.apply {
            layoutManager = LinearLayoutManager(context.applicationContext)
            adapter = mainMissionAdapter
            setHasFixedSize(true)
        }
    }
}