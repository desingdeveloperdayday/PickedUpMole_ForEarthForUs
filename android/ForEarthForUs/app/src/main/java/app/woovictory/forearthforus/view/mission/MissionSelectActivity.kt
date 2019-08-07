package app.woovictory.forearthforus.view.mission

import android.content.Context
import android.graphics.Point
import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import app.woovictory.forearthforus.R
import app.woovictory.forearthforus.base.BaseActivity
import app.woovictory.forearthforus.databinding.ActivityMissionSelectBinding
import app.woovictory.forearthforus.model.mission.MissionSelectResponse
import app.woovictory.forearthforus.utils.ItemDecoration
import app.woovictory.forearthforus.utils.missionCategoryTitleList
import app.woovictory.forearthforus.view.mission.adapter.MissionSelectAdapter
import app.woovictory.forearthforus.vm.mission.MissionSelectViewModel
import kotlinx.android.synthetic.main.activity_mission_select.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.koin.androidx.viewmodel.ext.android.viewModel


class MissionSelectActivity : BaseActivity<ActivityMissionSelectBinding>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_mission_select
    val viewModel: MissionSelectViewModel by viewModel()
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
    private var categoryId: Int = 0
    private var completeMessage: String = ""
    private var clickId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getData()
        getWindowSize()
        initToolbar()
        initStartView()
        setUpRecyclerView()
        setUpIndicator()
        subscribeViewModel()
    }

    private fun getData() {
        categoryId = intent.getIntExtra("categoryId", 0)
        completeMessage = intent.getStringExtra("completeMessage")
        Log.v("878723 Select", categoryId.toString())
        Log.v("878723 Select", completeMessage)
        // viewModelFeed 에 missionSelectList 요청.
        viewModel.getMissionSelectList(categoryId)
        setToolbarTitle(categoryId)

    }

    private fun setToolbarTitle(categoryId: Int) {
        viewDataBinding.missionSelectTitle.text = missionCategoryTitleList[categoryId - 1]
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

    // RecyclerView 설정.
    override fun initStartView() {
        viewDataBinding.apply {
            vm = viewModel
            lifecycleOwner = this@MissionSelectActivity
        }
    }

    private fun setUpRecyclerView() {
        missionSelectAdapter = MissionSelectAdapter { id: Int, imageView: ImageView, url: String ->
            clickId = id
            startDetailActivity(id, imageView, url)
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

    override fun subscribeViewModel() {
        // FIXME
        // 미션 시작하기 버튼을 클릭해서 이동하면 이미 미션을 시작한 상태이기 때문에
        // 하단 탭 버튼은 그만두기 / 미션 완료하기 형태가 되어야 한다.
        viewModel.clickToMissionStart.observe(this, Observer {
            toast("준비 중입니다.")
            // recyclerView 리스너 이용해서 보여지는 뷰의 포지션을 얻고 그에 해당하는 id 얻어야 함.
            //startActivity<MissionDetailActivity>()
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

        viewDataBinding.missionSelectToolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    // 일단 Shared Element Transition 우선순위 뒤로 미루기.
/*    private fun startDetailActivity(categoryId: Int, imageView: ImageView, url: String) {
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
            this
            , imageView, imageView.transitionName
        ).toBundle()

        Log.v("210323", imageView.toString())
        Log.v("210323", url)

        Intent(this, MissionDetailActivity::class.java)
            .putExtra("categoryId", categoryId)
            .putExtra("url", url)
            .let {
                startActivity(it, options)
            }
    }*/

    private fun startDetailActivity(id: Int, imageView: ImageView, url: String) {
        startActivity<MissionDetailActivity>(
            "id" to id,
            "completeMessage" to completeMessage
        )
    }
}
