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
import app.woovictory.forearthforus.util.SharedPreferenceManager
import app.woovictory.forearthforus.util.glide.GlideApp
import app.woovictory.forearthforus.vm.mission.MissionDetailViewModel
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.activity_mission_detail.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

class MissionDetailActivity : BaseActivity<ActivityMissionDetailBinding, MissionDetailViewModel>(),
    AppBarLayout.OnOffsetChangedListener {
    override val layoutResourceId: Int
        get() = R.layout.activity_mission_detail
    override val viewModel: MissionDetailViewModel by viewModel()

    var categoryId: Int = 0
    var url: String = ""
    var id: Int = 0

    override fun onEnterAnimationComplete() {
        super.onEnterAnimationComplete()
        Log.v("21032", "onEnter")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.v("21032", "onCreate")
        getData()
        initToolbar()
        initStartView()
        initDataBinding()

    }

    @SuppressLint("CheckResult")
    private fun getData() {
        categoryId = intent.getIntExtra("categoryId", 0)
        url = intent.getStringExtra("url")
        Log.v("21032", url)

        if (url != "main") {
            viewDataBinding.missionDetailImage.transitionName = url
            GlideApp.with(this).load(url).into(viewDataBinding.missionDetailImage)
        }
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
        }

        viewModel.missionDetailResponse.observe(this, Observer {
            id = it.feedId
            Log.v("2010023 detail",id.toString())
            if (it.status == "progress") {
                viewDataBinding.apply {
                    missionDetailSelectButtonLayout.visibility = View.GONE
                    missionDetailDecideButtonLayout.visibility = View.VISIBLE
                }
            } else {
                viewDataBinding.apply {
                    missionDetailSelectButtonLayout.visibility = View.VISIBLE
                    missionDetailDecideButtonLayout.visibility = View.GONE
                }
            }

        })

        // 미션 선택
        viewModel.clickToMissionSelect.observe(this, Observer {
            val mission = MissionSelectRequest(categoryId)
            viewModel.postMissionSelect(SharedPreferenceManager.token, mission)
            //startActivity<MissionCompleteActivity>()
        })

        // 미션 선택 후 결과 구독.
        viewModel.missionFeedResponse.observe(this, Observer {
            id = it.id
            Log.v("2010023",id.toString())
            if (it.mission.status == "progress") {
                viewDataBinding.apply {
                    missionDetailSelectButtonLayout.visibility = View.GONE
                    missionDetailDecideButtonLayout.visibility = View.VISIBLE
                }
            }

        })

        viewModel.clickToMissionCancel.observe(this, Observer {
            toast("그만 두기 버튼 클릭")
        })

        viewModel.clickToMissionComplete.observe(this, Observer {
            startActivity<MissionCompleteActivity>("id" to id)
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
