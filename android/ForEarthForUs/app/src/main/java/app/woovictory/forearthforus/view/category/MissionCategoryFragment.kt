package app.woovictory.forearthforus.view.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import app.woovictory.forearthforus.R
import app.woovictory.forearthforus.databinding.FragmentMissionCategoryBinding
import app.woovictory.forearthforus.view.mission.MissionSelectActivity
import app.woovictory.forearthforus.view.category.adapter.MissionCategoryAdapter
import app.woovictory.forearthforus.vm.category.MissionCategoryViewModel
import org.jetbrains.anko.support.v4.startActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by VictoryWoo
 */
class MissionCategoryFragment : Fragment() {

    private lateinit var fragmentMissionDataBinding: FragmentMissionCategoryBinding
    private var missionCategoryAdapter: MissionCategoryAdapter? = null
        set(value) {
            field = value
            field?.onMissionItemClickListener = { categoryId ->
                startMissionSelectActivity(categoryId)
            }
        }
    private val missionCategoryViewModel: MissionCategoryViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentMissionDataBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_mission_category,
            container, false
        )
        return fragmentMissionDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        missionCategoryAdapter = MissionCategoryAdapter()
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
        // mission category 리스트를 불러오는 함수 호출.
        missionCategoryViewModel.getMissionCategoryList()

        missionCategoryViewModel.missionCategoryResponse.observe(this, Observer {
            missionCategoryAdapter?.addItem(it)
        })
    }


    private fun initRecyclerView() {
        fragmentMissionDataBinding.missionRv.apply {
            layoutManager = LinearLayoutManager(context.applicationContext)
            adapter = missionCategoryAdapter
            setHasFixedSize(true)
        }
    }

    private fun startMissionSelectActivity(categoryId: Int) {
        startActivity<MissionSelectActivity>("categoryId" to categoryId)
    }

    companion object {
        fun newInstance(): MissionCategoryFragment {
            return MissionCategoryFragment()
        }
    }

}