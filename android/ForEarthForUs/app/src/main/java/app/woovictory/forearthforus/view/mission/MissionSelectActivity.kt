package app.woovictory.forearthforus.view.mission

import android.content.Context
import android.graphics.Point
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.WindowManager
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import app.woovictory.forearthforus.R
import app.woovictory.forearthforus.base.BaseActivity
import app.woovictory.forearthforus.databinding.ActivityMissionSelectBinding
import app.woovictory.forearthforus.model.mission.MissionSelectResponse
import app.woovictory.forearthforus.util.ItemDecoration
import app.woovictory.forearthforus.view.mission.adapter.MissionSelectAdapter
import app.woovictory.forearthforus.vm.MissionSelectViewModel
import kotlinx.android.synthetic.main.activity_mission_select.*
import org.jetbrains.anko.startActivity

class MissionSelectActivity : BaseActivity<ActivityMissionSelectBinding, MissionSelectViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_mission_select
    override val viewModel: MissionSelectViewModel = MissionSelectViewModel()
    lateinit var items: ArrayList<MissionSelectResponse>
    private var missionSelectAdapter: MissionSelectAdapter? = null
    private lateinit var size: Point

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        missionSelectAdapter = MissionSelectAdapter()
        getWindowSize()
        initToolbar()
        initMockData()
        initStartView()
        setIndicator()

        // 일단 간단하게 화면 이어 붙이기 위한 용도.
        missionSelectButton.setOnClickListener {
            startActivity<MissionDetailActivity>()
        }
    }

    private fun getWindowSize() {
        val wm = this.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = wm.defaultDisplay
        size = Point()
        display.getSize(size)
    }

    private fun initToolbar() {
        setSupportActionBar(missionSelectToolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        missionSelectToolbar.navigationIcon?.setColorFilter(
            ContextCompat.getColor(this, R.color.colorBlack)
            , PorterDuff.Mode.SRC_ATOP
        )
    }

    // 초기 가데이터.
    private fun initMockData() {
        items = ArrayList()
        for (i in 0..5) {
            items.add(
                MissionSelectResponse(
                    R.drawable.fufe_illust_jh_04,
                    "지역 농산물 이용하기",
                    "옆 동네 토마토를 가져오기 위해서는 많은 연료가 필요하다고 해요! 토마토가 우리의 식탁에 오르기까지의 이산화탄소를 줄이기 위해 지역농산품을 이용해봐요!"
                )
            )
        }
    }

    // RecyclerView 설정.
    override fun initStartView() {
        viewDataBinding.missionSelectRv.apply {
            layoutManager = LinearLayoutManager(applicationContext, LinearLayout.HORIZONTAL, false)
            adapter = missionSelectAdapter
            setHasFixedSize(true)
            addItemDecoration(ItemDecoration(size.x / 45, size.x / 15))
        }
        missionSelectAdapter?.addItem(items)
    }

    private fun setIndicator() {
        val pagerSnapHelper = PagerSnapHelper()
        pagerSnapHelper.attachToRecyclerView(viewDataBinding.missionSelectRv)
        viewDataBinding.missionSelectIndicator.attachToRecyclerView(viewDataBinding.missionSelectRv, pagerSnapHelper)
        missionSelectAdapter?.registerAdapterDataObserver(viewDataBinding.missionSelectIndicator.adapterDataObserver)
    }

    override fun initDataBinding() {

    }

}
