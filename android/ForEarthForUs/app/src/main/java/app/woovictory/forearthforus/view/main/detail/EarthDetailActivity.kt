package app.woovictory.forearthforus.view.main.detail

import android.content.Context
import android.graphics.Point
import android.os.Bundle
import android.view.WindowManager
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import app.woovictory.forearthforus.R
import app.woovictory.forearthforus.base.BaseActivity
import app.woovictory.forearthforus.databinding.ActivityEarthDetailBinding
import app.woovictory.forearthforus.util.GridItemDecoration
import app.woovictory.forearthforus.util.LEVEL
import app.woovictory.forearthforus.util.MISSION_STATUS_COMPLETE
import app.woovictory.forearthforus.util.SharedPreferenceManager
import app.woovictory.forearthforus.view.mypage.adapter.AchieveListAdapter
import app.woovictory.forearthforus.vm.mypage.AchieveListViewModel
import org.jetbrains.anko.dip
import org.koin.androidx.viewmodel.ext.android.viewModel

class EarthDetailActivity : BaseActivity<ActivityEarthDetailBinding, AchieveListViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_earth_detail
    override val viewModel: AchieveListViewModel by viewModel()
    //get() = ViewModelProviders.of(this@EarthDetailActivity).get(AchieveListViewModel::class.java)

    private var achieveListAdapter = AchieveListAdapter()
    private val itemCount: Int = 2
    private lateinit var size: Point

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getAchieveList(SharedPreferenceManager.token, MISSION_STATUS_COMPLETE)

        getWindowSize()
        initStartView()
        initDataBinding()
    }

    override fun initStartView() {
        viewDataBinding.apply {
            vm = viewModel
            lifecycleOwner = this@EarthDetailActivity
            earthDetailLevel.text = "$LEVEL${SharedPreferenceManager.earthLevel} ${SharedPreferenceManager.userName}"
            earthDetailState.text = SharedPreferenceManager.userContent.replace("\n", "")
        }
    }

    override fun initDataBinding() {
        viewModel.clickToBack.observe(this, Observer {
            finish()
        })

        viewModel.achieveListResponse.observe(this, Observer {
            if (it.isNotEmpty()) {
                achieveListAdapter.addAllItem(it)
                setUpRecyclerView()
            }
        })
    }

    private fun getWindowSize() {
        val windowManager = this.applicationContext?.getSystemService(Context.WINDOW_SERVICE)
                as WindowManager
        val display = windowManager.defaultDisplay
        size = Point()
        display.getSize(size)
    }

    private fun setUpRecyclerView() {
        viewDataBinding.earthDetailAchieveRv.apply {
            adapter = achieveListAdapter
            layoutManager = GridLayoutManager(this@EarthDetailActivity, itemCount)
            val space = dip(20)
            val side = dip(8)
            addItemDecoration(GridItemDecoration(space, side))
        }
    }
}