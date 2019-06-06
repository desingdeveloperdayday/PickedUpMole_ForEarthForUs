package app.woovictory.forearthforus.view.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import app.woovictory.forearthforus.R
import app.woovictory.forearthforus.databinding.FragmentMainBinding
import app.woovictory.forearthforus.model.mission.MissionResponse
import app.woovictory.forearthforus.util.SharedPreferenceManager
import app.woovictory.forearthforus.util.glide.GlideApp
import app.woovictory.forearthforus.view.main.detail.EarthDetailActivity
import app.woovictory.forearthforus.vm.MainViewModel
import org.jetbrains.anko.support.v4.startActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

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
    private val mainViewModel: MainViewModel by viewModel()
    private var earthLevelList = arrayListOf(R.drawable.main_bar_graph1, R.drawable.main_bar_graph2)


    //get() = ViewModelProviders.of(this@MainFragment).get(MainViewModel::class.java)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentMainBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        return fragmentMainBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initMockData()
        initRecyclerView()
        setUpViewModel()
        setUpDataBinding()

        fragmentMainBinding.itemMainEarthUserName.text = SharedPreferenceManager.userName
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

    private fun setUpViewModel() {
        fragmentMainBinding.apply {
            vm = mainViewModel
            lifecycleOwner = this@MainFragment
        }
    }

    private fun setUpDataBinding() {
        mainViewModel.getEarthInformation()
        mainViewModel.getMissionFeed()

        mainViewModel.clickToEarthDetail.observe(this, Observer {
            startActivity<EarthDetailActivity>()
        })

        mainViewModel.earthResponse.observe(this, Observer {
            Log.v("40032", it.earthLevel.toString())
            setEarthLevel(it.earthLevel - 1)

        })

    }

    private fun setEarthLevel(earthLevel: Int) {
        when (earthLevel) {
            0 -> GlideApp.with(this).load(earthLevelList[earthLevel]).into(fragmentMainBinding.mainBarGraph)
            1 -> GlideApp.with(this).load(earthLevelList[earthLevel]).into(fragmentMainBinding.mainBarGraph)
            2 -> GlideApp.with(this).load(earthLevelList[earthLevel]).into(fragmentMainBinding.mainBarGraph)
            3 -> GlideApp.with(this).load(earthLevelList[earthLevel]).into(fragmentMainBinding.mainBarGraph)
            4 -> GlideApp.with(this).load(earthLevelList[earthLevel]).into(fragmentMainBinding.mainBarGraph)
            5 -> GlideApp.with(this).load(earthLevelList[earthLevel]).into(fragmentMainBinding.mainBarGraph)
            6 -> GlideApp.with(this).load(earthLevelList[earthLevel]).into(fragmentMainBinding.mainBarGraph)
            7 -> GlideApp.with(this).load(earthLevelList[earthLevel]).into(fragmentMainBinding.mainBarGraph)
        }
    }
}