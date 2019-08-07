package app.woovictory.forearthforus.view.mypage.achieve

import android.content.Context
import android.graphics.Point
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import app.woovictory.forearthforus.R
import app.woovictory.forearthforus.base.BaseActivity
import app.woovictory.forearthforus.databinding.ActivityAchieveListBinding
import app.woovictory.forearthforus.utils.GridItemDecoration
import app.woovictory.forearthforus.utils.MISSION_STATUS_COMPLETE
import app.woovictory.forearthforus.data.SharedPreferenceManager
import app.woovictory.forearthforus.view.mypage.adapter.AchieveListAdapter
import app.woovictory.forearthforus.vm.mypage.AchieveListViewModel
import org.jetbrains.anko.dip
import org.koin.androidx.viewmodel.ext.android.viewModel

class AchieveListActivity : BaseActivity<ActivityAchieveListBinding>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_achieve_list
    val viewModel: AchieveListViewModel by viewModel()

    //ViewModelProviders.of(this@AchieveListActivity).get(AchieveListViewModel::class.java)
    private var achieveListAdapter = AchieveListAdapter()
    private val itemCount: Int = 2
    private lateinit var size: Point

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getAchieveList(SharedPreferenceManager.token, MISSION_STATUS_COMPLETE)

        getWindowSize()
        initStartView()
        subscribeViewModel()
    }

    private fun getWindowSize() {
        val windowManager = this.applicationContext?.getSystemService(Context.WINDOW_SERVICE)
                as WindowManager
        val display = windowManager.defaultDisplay
        size = Point()
        display.getSize(size)
    }

    override fun initStartView() {
        viewDataBinding.apply {
            vm = viewModel
            lifecycleOwner = this@AchieveListActivity
        }
    }

    override fun subscribeViewModel() {
        viewModel.clickToBack.observe(this, Observer {
            finish()
        })

        viewModel.achieveListResponse.observe(this, Observer {
            if (it.isNotEmpty()) {
                achieveListAdapter.addAllItem(it)
                setUpRecyclerView()
            } else {
                Log.v("109203", "비어있음.")
                viewDataBinding.apply {
                    achieveImageNull.visibility = View.VISIBLE
                    achieveListRv.visibility = View.GONE
                }
            }
        })

        viewModel.isLoading.observe(this, Observer {
            if (it) {
                viewDataBinding.loading.visibility = View.VISIBLE
            } else {
                viewDataBinding.loading.visibility = View.GONE
            }
        })
    }

    private fun setUpRecyclerView() {
        viewDataBinding.achieveListRv.apply {
            adapter = achieveListAdapter
            layoutManager = GridLayoutManager(this@AchieveListActivity, itemCount)
            val space = dip(20)
            val side = dip(8)
            addItemDecoration(GridItemDecoration(space, side))
        }
    }
}