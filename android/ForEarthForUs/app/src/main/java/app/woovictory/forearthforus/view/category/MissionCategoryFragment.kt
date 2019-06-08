package app.woovictory.forearthforus.view.category

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
import app.woovictory.forearthforus.databinding.FragmentMissionCategoryBinding
import app.woovictory.forearthforus.model.TodayMissionResponse
import app.woovictory.forearthforus.model.category.MissionCategoryResponse
import app.woovictory.forearthforus.view.mission.MissionSelectActivity
import app.woovictory.forearthforus.view.mission.adapter.TodayMissionAdapter
import app.woovictory.forearthforus.vm.category.MissionCategoryViewModel
import org.jetbrains.anko.support.v4.startActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by VictoryWoo
 */
class MissionCategoryFragment : Fragment() {

    companion object {
        private val ARGUMENT = "MissionCategoryFragment"
        private var categoryFragment: MissionCategoryFragment? = null

        fun newInstance(): MissionCategoryFragment? {
            return MissionCategoryFragment()
        }
    }

    private lateinit var fragmentMissionDataBinding: FragmentMissionCategoryBinding
    private var todayMissionAdapter: TodayMissionAdapter? = null
        set(value) {
            field = value
            field?.onMissionItemClickListener = { startMissionSelectActivity() }
        }
    private val missionCategoryViewModel: MissionCategoryViewModel by viewModel()

    //private lateinit var itemList: ArrayList<MissionCategoryResponse>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentMissionDataBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_mission_category,
            container, false
        )
        return fragmentMissionDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        todayMissionAdapter = TodayMissionAdapter()
        initRecyclerView()
        initStartView()
        initDataBinding()
    }

    private fun initStartView() {
        fragmentMissionDataBinding.apply {
            vm = missionCategoryViewModel
            lifecycleOwner = this@MissionCategoryFragment
        }
    }

    private fun initDataBinding() {
        missionCategoryViewModel.getMissionCategoryList()

        missionCategoryViewModel.missionCategoryResponse.observe(this, Observer {
            Log.v("991239 전",it.size.toString())
            todayMissionAdapter?.addItem(it)
            Log.v("991239 후",it.size.toString())
        })
    }


    private fun initRecyclerView() {
        fragmentMissionDataBinding.missionRv.apply {
            layoutManager = LinearLayoutManager(context.applicationContext)
            adapter = todayMissionAdapter
            setHasFixedSize(true)
        }
    }

    private fun startMissionSelectActivity() {
        startActivity<MissionSelectActivity>()
    }

}