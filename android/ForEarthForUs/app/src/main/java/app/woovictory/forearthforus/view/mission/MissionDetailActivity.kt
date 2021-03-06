package app.woovictory.forearthforus.view.mission

import android.annotation.SuppressLint
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
import app.woovictory.forearthforus.utils.MISSION_STATUS_COMPLETE
import app.woovictory.forearthforus.utils.MISSION_STATUS_NEW
import app.woovictory.forearthforus.utils.MISSION_STATUS_PROGRESS
import app.woovictory.forearthforus.data.SharedPreferenceManager
import app.woovictory.forearthforus.vm.mission.MissionDetailViewModel
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.activity_mission_detail.*
import org.jetbrains.anko.startActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MissionDetailActivity : BaseActivity<ActivityMissionDetailBinding>(),
    AppBarLayout.OnOffsetChangedListener {
    override val layoutResourceId: Int
        get() = R.layout.activity_mission_detail
    val viewModel: MissionDetailViewModel by viewModel()

    private var id: Int = 0
    private var category: Int = 0
    private var feedId: String = ""
    private var title: String = ""
    private var imageUrl: String = ""
    private var completeMessage: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getData()
        initToolbar()
        initStartView()
        subscribeViewModel()

    }

    @SuppressLint("CheckResult")
    private fun getData() {
        id = intent.getIntExtra("id", 0)
        completeMessage = intent.getStringExtra("completeMessage")

        // main 에서 왔을 때.
        if (intent.getStringExtra("main") != null) {

        }
    }


    override fun initStartView() {
        viewDataBinding.apply {
            vm = viewModel
            lifecycleOwner = this@MissionDetailActivity
        }
        viewModel.getMissionDetailInformation(SharedPreferenceManager.token, id)
    }

    override fun subscribeViewModel() {
        viewDataBinding.missionDetailToolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        viewModel.missionDetailResponse.observe(this, Observer {
            feedId = it.feedId
            title = it.title
            imageUrl = it.image
            category = it.category
            Log.v("2010023 detail", feedId)
            Log.v("2010023 detail", it.image)
            Log.v("2010023 category", it.category.toString())
            when {
                it.status == MISSION_STATUS_PROGRESS -> viewDataBinding.apply {
                    missionDetailSelectButtonLayout.visibility = View.GONE
                    missionDetailDecideButtonLayout.visibility = View.VISIBLE
                    //SharedPreferenceManager.missionCompleteStatus = true
                }
                it.status == MISSION_STATUS_NEW -> viewDataBinding.apply {
                    missionDetailSelectButtonLayout.visibility = View.VISIBLE
                    missionDetailDecideButtonLayout.visibility = View.GONE
                    //SharedPreferenceManager.missionCompleteStatus = false
                }
                it.status == MISSION_STATUS_COMPLETE -> {
                    Log.v("2010023 status", it.status)
                    missionDetailSelectButtonLayout.apply {
                        visibility = View.VISIBLE
                        setBackgroundResource(R.drawable.border_button_background_inactive)
                        missionDetailSelectText.text = "완료된 미션"
                    }
                }
            }

        })

        // 미션 선택
        viewModel.clickToMissionSelect.observe(this, Observer {
            val mission = MissionSelectRequest(id)
            viewModel.postMissionSelect(SharedPreferenceManager.token, mission)
            //startActivity<MissionCompleteActivity>()
        })

        // 미션 선택 후 결과 구독.
        viewModel.missionFeedResponse.observe(this, Observer {
            feedId = it.id
            title = it.mission.title
            Log.v("2010023", feedId)
            if (it.mission.status == MISSION_STATUS_PROGRESS) {
                viewDataBinding.apply {
                    missionDetailSelectButtonLayout.visibility = View.GONE
                    missionDetailDecideButtonLayout.visibility = View.VISIBLE
                    //SharedPreferenceManager.missionCompleteStatus = true
                }
            }
        })

        viewModel.clickToMissionCancel.observe(this, Observer {
            //toast("그만 두기 버튼 클릭")
        })

        // 미션 완료하기 버튼 클릭.
        viewModel.clickToMissionComplete.observe(this, Observer {
            startActivity<MissionCompleteActivity>(
                "feedId" to feedId,
                "title" to title,
                "imageUrl" to imageUrl,
                "id" to id,
                "completeMessage" to completeMessage,
                "category" to category
            )
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
