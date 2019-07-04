package app.woovictory.forearthforus.view.category

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import app.woovictory.forearthforus.R
import app.woovictory.forearthforus.base.BaseFragment
import app.woovictory.forearthforus.databinding.FragmentMissionCategoryBinding
import app.woovictory.forearthforus.view.category.adapter.MissionCategoryAdapter
import app.woovictory.forearthforus.view.mission.MissionSelectActivity
import app.woovictory.forearthforus.vm.category.MissionCategoryViewModel
import org.jetbrains.anko.support.v4.startActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by VictoryWoo
 */
class MissionCategoryFragment : BaseFragment<FragmentMissionCategoryBinding, MissionCategoryViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_mission_category
    override val viewModel: MissionCategoryViewModel by viewModel()

    private var missionCategoryAdapter: MissionCategoryAdapter? = null
        set(value) {
            field = value
            field?.onMissionItemClickListener = { categoryId, completeMessage ->
                startMissionSelectActivity(categoryId, completeMessage)
            }
        }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        missionCategoryAdapter = MissionCategoryAdapter()
        initRecyclerView()
        initStartView()
        subscribeViewModel()
    }

    override fun initStartView() {
        viewDataBinding.apply {
            vm = viewModel
            lifecycleOwner = this@MissionCategoryFragment
        }
        // mission category 리스트를 불러오는 함수 호출.
        viewModel.getMissionCategoryList()
    }

    override fun subscribeViewModel() {
        viewModel.missionCategoryResponse.observe(this, Observer {
            missionCategoryAdapter?.addItem(it)
        })
    }

    private fun initRecyclerView() {
        viewDataBinding.missionRv.apply {
            scrollToPosition(0)
            layoutManager = LinearLayoutManager(context.applicationContext)
            adapter = missionCategoryAdapter
            setHasFixedSize(true)
        }
    }

    private fun startMissionSelectActivity(categoryId: Int, completeMessage: String) {
        startActivity<MissionSelectActivity>(
            "categoryId" to categoryId,
            "completeMessage" to completeMessage
        )
    }

    companion object {
        fun newInstance(): MissionCategoryFragment {
            return MissionCategoryFragment()
        }
    }
}