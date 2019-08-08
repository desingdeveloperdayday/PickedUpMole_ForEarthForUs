package app.woovictory.forearthforus.view.main.detail

import android.content.Context
import android.graphics.Point
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import app.woovictory.forearthforus.R
import app.woovictory.forearthforus.base.BaseActivity
import app.woovictory.forearthforus.data.SharedPreferenceManager
import app.woovictory.forearthforus.databinding.ActivityEarthDetailBinding
import app.woovictory.forearthforus.utils.*
import app.woovictory.forearthforus.utils.decoration.GridItemDecoration
import app.woovictory.forearthforus.utils.extensions.loadDrawableImage
import app.woovictory.forearthforus.view.mypage.adapter.AchieveListAdapter
import app.woovictory.forearthforus.vm.mypage.AchieveListViewModel
import org.jetbrains.anko.dip
import org.jetbrains.anko.textColor
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.StringBuilder

class EarthDetailActivity : BaseActivity<ActivityEarthDetailBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_earth_detail
    val viewModel: AchieveListViewModel by viewModel()

    private var achieveListAdapter = AchieveListAdapter()
    private lateinit var size: Point

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getAchieveList(SharedPreferenceManager.token, MISSION_STATUS_COMPLETE)

        getData()
        getWindowSize()
        initStartView()
        subscribeViewModel()
    }

    private fun getData() {
        viewDataBinding.run {
            earthDetailBar.loadDrawableImage(earthLevelList[SharedPreferenceManager.earthLevel - 1])
            earthImage.loadDrawableImage(earthStatusList[SharedPreferenceManager.earthLevel - 1])
        }
    }

    override fun initStartView() {
        viewDataBinding.apply {
            vm = viewModel
            lifecycleOwner = this@EarthDetailActivity
            SharedPreferenceManager.apply {
                val sb = StringBuilder()
                sb.append(LEVEL).append(earthLevel).append(userName)
                earthDetailLevel.text = "$sb"
                earthDetailState.text = userContent
                if (earthLevel <= 4) {
                    earthDetailLevel.textColor = ContextCompat.getColor(this@EarthDetailActivity, R.color.fe_fu_sub)
                } else {
                    earthDetailLevel.textColor = ContextCompat.getColor(this@EarthDetailActivity, R.color.fe_fu_main)
                }
            }
        }
    }

    override fun subscribeViewModel() {
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
            layoutManager = GridLayoutManager(this@EarthDetailActivity, ITEM_COUNT)
            addItemDecoration(
                GridItemDecoration(
                    space = dip(20),
                    side = dip(8)
                )
            )
        }
    }
}