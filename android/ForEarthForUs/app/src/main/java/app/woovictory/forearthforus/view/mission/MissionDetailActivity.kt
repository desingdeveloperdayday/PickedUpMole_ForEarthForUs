package app.woovictory.forearthforus.view.mission

import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.lifecycle.Observer
import app.woovictory.forearthforus.R
import app.woovictory.forearthforus.base.BaseActivity
import app.woovictory.forearthforus.databinding.ActivityMissionDetailBinding
import app.woovictory.forearthforus.model.mission.MissionSelectRequest
import app.woovictory.forearthforus.util.SharedPreferenceManager
import app.woovictory.forearthforus.vm.mission.MissionDetailViewModel
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.activity_mission_detail.*
import org.jetbrains.anko.startActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MissionDetailActivity : BaseActivity<ActivityMissionDetailBinding, MissionDetailViewModel>(),
    AppBarLayout.OnOffsetChangedListener {
    override val layoutResourceId: Int
        get() = R.layout.activity_mission_detail
    override val viewModel: MissionDetailViewModel by viewModel()

    var categoryId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getData()
        initToolbar()
        initStartView()
        initDataBinding()

        // 임시 방편.
        missionDetailCompleteButton.setOnClickListener {
            startActivity<MissionCompleteActivity>()
            //startActivity<MissionCompleteWriteActivity>()
        }
    }

    private fun getData() {
        categoryId = intent.getIntExtra("categoryId", 0)
        Log.v("1823899", categoryId.toString())
        viewModel.getMissionDetailInformation(SharedPreferenceManager.token, categoryId)
    }


    override fun initStartView() {
        viewDataBinding.apply {
            vm = viewModel
            lifecycleOwner = this@MissionDetailActivity
        }
    }

    override fun initDataBinding() {
        viewDataBinding.missionDetailToolbar.setNavigationOnClickListener {
            onBackPressed()
            Log.v("18238", it.toString())
            //finish()
        }

        viewModel.clickToMissionSelect.observe(this, Observer {
            /*val mission = MissionSelectRequest(categoryId)
            viewModel.postMissionSelect(SharedPreferenceManager.token, mission)*/
            startActivity<MissionCompleteActivity>()
        })
    }

    private fun initToolbar() {
        setSupportActionBar(missionDetailToolbar)
        // toolbar title not showing
        supportActionBar?.setDisplayShowTitleEnabled(false)
        // toolbar back button icon showing
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        missionDetailToolbar.navigationIcon?.setColorFilter(
            ContextCompat.getColor(this, R.color.colorBlack)
            , PorterDuff.Mode.SRC_ATOP
        )

        missionDetailCollapsingToolbar.setCollapsedTitleTextColor(
            ContextCompat.getColor(
                this
                , R.color.colorBlack
            )
        )
        // listener 등록.
        missionDetailAppbar.addOnOffsetChangedListener(this@MissionDetailActivity)

    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
        if (missionDetailCollapsingToolbar.height + verticalOffset < 2 * ViewCompat.getMinimumHeight(
                missionDetailCollapsingToolbar
            )
        ) {

            // collapsingToolbar의 title 설정을 위함.
            missionDetailCollapsingToolbar.isTitleEnabled = true
            missionDetailCollapsingToolbar.title = missionDetailTitle.text.toString()
            missionDetailToolbar.navigationIcon?.setColorFilter(
                ContextCompat.getColor(this, R.color.colorBlack),
                PorterDuff.Mode.SRC_ATOP
            )
        } else {
            missionDetailCollapsingToolbar.isTitleEnabled = false
            /*missionDetailToolbar.navigationIcon?.setColorFilter(
                ContextCompat.getColor(this, R.color.fefu_white),
                PorterDuff.Mode.SRC_ATOP
            )*/
        }
    }
}
