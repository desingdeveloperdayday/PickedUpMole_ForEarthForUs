package app.woovictory.forearthforus.view.mission

import android.content.Context
import android.graphics.Point
import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import app.woovictory.forearthforus.R
import app.woovictory.forearthforus.base.BaseActivity
import app.woovictory.forearthforus.databinding.ActivityMissionSelectBinding
import app.woovictory.forearthforus.model.mission.MissionSelectResponse
import app.woovictory.forearthforus.util.ItemDecoration
import app.woovictory.forearthforus.view.mission.adapter.MissionSelectAdapter
import app.woovictory.forearthforus.vm.mission.MissionSelectViewModel
import kotlinx.android.synthetic.main.activity_mission_select.*
import org.jetbrains.anko.startActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MissionSelectActivity : BaseActivity<ActivityMissionSelectBinding
        , MissionSelectViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_mission_select
    override val viewModel: MissionSelectViewModel by viewModel()
    private lateinit var items: ArrayList<MissionSelectResponse>
    private var missionSelectAdapter: MissionSelectAdapter? = null
    /*set(value) {
        field = value
        field?.onMissionSelectItemClickListener = { position ->
            Log.v("878723","1")
            startDetailActivity(position)
        }
    }*/

    private lateinit var size: Point

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getData()
        getWindowSize()
        initToolbar()
        initStartView()
        setUpRecyclerView()
        setUpIndicator()
        initDataBinding()
    }

    private fun getData() {
        val categoryId = intent.getIntExtra("categoryId", 0)
        Log.v("7786", categoryId.toString())

        // viewModel 에 missionSelectList 요청.
        viewModel.getMissionSelectList(categoryId)
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

        viewDataBinding.missionSelectToolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    // RecyclerView 설정.
    override fun initStartView() {
        viewDataBinding.apply {
            vm = viewModel
            lifecycleOwner = this@MissionSelectActivity
        }
    }

    private fun setUpRecyclerView() {
        missionSelectAdapter = MissionSelectAdapter {
            startDetailActivity(it)
        }

        viewDataBinding.missionSelectRv.apply {
            layoutManager = LinearLayoutManager(applicationContext, LinearLayout.HORIZONTAL, false)
            adapter = missionSelectAdapter
            setHasFixedSize(true)
            addItemDecoration(ItemDecoration(size.x / 45, size.x / 15))
        }
        //missionSelectAdapter.addItem(items)
    }

    private fun setUpIndicator() {
        val pagerSnapHelper = PagerSnapHelper()
        pagerSnapHelper.attachToRecyclerView(viewDataBinding.missionSelectRv)
        viewDataBinding.missionSelectIndicator.attachToRecyclerView(viewDataBinding.missionSelectRv, pagerSnapHelper)
        missionSelectAdapter?.registerAdapterDataObserver(viewDataBinding.missionSelectIndicator.adapterDataObserver)
    }

    override fun initDataBinding() {
        // FIXME
        // 미션 시작하기 버튼을 클릭해서 이동하면 이미 미션을 시작한 상태이기 때문에
        // 하단 탭 버튼은 그만두기 / 미션 완료하기 형태가 되어야 한다.
        viewModel.clickToMissionStart.observe(this, Observer {
            startActivity<MissionDetailActivity>()
        })

        viewModel.missionSelectResponse.observe(this, Observer {
            if (it.size != 0) {
                missionSelectAdapter?.addItem(it)
            }
        })

        viewModel.isLoading.observe(this, Observer { loading ->
            if (loading) {
                viewDataBinding.loading.visibility = View.VISIBLE
            } else {
                viewDataBinding.loading.visibility = View.GONE
            }
        })
    }

    private fun startDetailActivity(categoryId: Int) {
        startActivity<MissionDetailActivity>("categoryId" to categoryId)
    }

}
