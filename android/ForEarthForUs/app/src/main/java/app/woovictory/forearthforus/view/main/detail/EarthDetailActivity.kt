package app.woovictory.forearthforus.view.main.detail

import android.content.Context
import android.graphics.Point
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import app.woovictory.forearthforus.R
import app.woovictory.forearthforus.base.BaseActivity
import app.woovictory.forearthforus.databinding.ActivityEarthDetailBinding
import app.woovictory.forearthforus.util.*
import app.woovictory.forearthforus.view.mypage.adapter.AchieveListAdapter
import app.woovictory.forearthforus.vm.mypage.AchieveListViewModel
import org.jetbrains.anko.dip
import org.jetbrains.anko.textColor
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

        getData()
        getWindowSize()
        initStartView()
        initDataBinding()
    }

    private fun getData() {
        val earthLevel = SharedPreferenceManager.earthLevel - 1
        loadDrawableImage(viewDataBinding.earthDetailBar, earthLevelList[earthLevel])
        loadDrawableImage(viewDataBinding.earthImage, earthStatusList[earthLevel])
    }

    override fun initStartView() {
        viewDataBinding.apply {
            vm = viewModel
            lifecycleOwner = this@EarthDetailActivity
            SharedPreferenceManager.apply {
                earthDetailLevel.text = "$LEVEL$earthLevel $userName"
                earthDetailState.text = userContent
                if (earthLevel <= 4) {
                    earthDetailLevel.textColor = ContextCompat.getColor(this@EarthDetailActivity, R.color.fe_fu_sub)
                } else {
                    earthDetailLevel.textColor = ContextCompat.getColor(this@EarthDetailActivity, R.color.fe_fu_main)
                }

            }

        }
    }

    override fun initDataBinding() {
        viewModel.clickToBack.observe(this, Observer {
            finish()
        })

        viewModel.achieveListResponse.observe(this, Observer {
            if (it.isNotEmpty()) {
                viewDataBinding.apply {
                    earthDetailAchieveRv.visibility = View.VISIBLE
                    earthDetailAchieved.visibility = View.VISIBLE
                    achievedNullIconImage.visibility = View.GONE
                }
                achieveListAdapter.addAllItem(it)
                setUpRecyclerView()
            } else {
                viewDataBinding.apply {
                    achievedNullIconImage.visibility = View.VISIBLE
                    earthDetailAchieveRv.visibility = View.GONE
                    earthDetailAchieved.visibility = View.GONE
                }
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