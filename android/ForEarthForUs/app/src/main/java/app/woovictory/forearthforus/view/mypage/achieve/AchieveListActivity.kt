package app.woovictory.forearthforus.view.mypage.achieve

import android.content.Context
import android.graphics.Point
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import app.woovictory.forearthforus.R
import app.woovictory.forearthforus.base.BaseActivity
import app.woovictory.forearthforus.databinding.ActivityAchieveListBinding
import app.woovictory.forearthforus.model.mypage.AchieveResponseMock
import app.woovictory.forearthforus.util.GridItemDecoration
import app.woovictory.forearthforus.view.mypage.adapter.AchieveListAdapter
import app.woovictory.forearthforus.vm.mypage.AchieveListViewModel
import org.jetbrains.anko.dip

class AchieveListActivity : BaseActivity<ActivityAchieveListBinding, AchieveListViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_achieve_list
    override val viewModel: AchieveListViewModel
        get() = ViewModelProviders.of(this@AchieveListActivity)
            .get(AchieveListViewModel::class.java)
    private var achieveListAdapter = AchieveListAdapter()
    private var itemList = ArrayList<AchieveResponseMock>()
    private val itemCount: Int = 2
    private lateinit var size: Point

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindowSize()
        setUpMockData()
        initStartView()
        setUpRecyclerView()
        initDataBinding()
    }

    private fun getWindowSize() {
        val windowManager = this.applicationContext?.getSystemService(Context.WINDOW_SERVICE)
                as WindowManager
        val display = windowManager.defaultDisplay
        size = Point()
        display.getSize(size)
    }

    private fun setUpMockData() {
        for (i in 0..7) {
            itemList.add(AchieveResponseMock(R.drawable.fufe_illust_jh_04, "지역 농산물 이용하기"))
        }
    }

    override fun initStartView() {
        viewDataBinding.apply {
            vm = viewModel
            lifecycleOwner = this@AchieveListActivity
        }
    }

    private fun setUpRecyclerView() {
        viewDataBinding.achieveListRv.apply {
            adapter = achieveListAdapter
            layoutManager = GridLayoutManager(this@AchieveListActivity, itemCount)
            val space = dip(20)
            val side = dip(8)
            addItemDecoration(GridItemDecoration(space, side))
            Log.v("9988990", "size.x : ${size.x}")
            Log.v("9988990", "size.y : ${size.y}")
            Log.v("9988990", "space : $space")
            Log.v("9988990", "side : $side")
        }
        achieveListAdapter.addAllItem(itemList)
    }

    override fun initDataBinding() {
        viewModel.clickToBack.observe(this, Observer {
            finish()
        })
    }
}
