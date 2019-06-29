package app.woovictory.forearthforus.view.mission

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.graphics.drawable.PictureDrawable
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
import app.woovictory.forearthforus.util.glide.SvgSoftwareLayerSetter
import app.woovictory.forearthforus.vm.mission.MissionDetailViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.Request
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
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
    var url: String = ""

    override fun onEnterAnimationComplete() {
        super.onEnterAnimationComplete()
        Log.v("21032", "onEnter")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.v("21032", "onCreate")
        postponeEnterTransition()
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

    @SuppressLint("CheckResult")
    private fun getData() {
        categoryId = intent.getIntExtra("categoryId", 0)
        url = intent.getStringExtra("url")
        Log.v("1823899", categoryId.toString())
        Log.v("1823899", url)

        supportPostponeEnterTransition()

        viewDataBinding.missionDetailImage.transitionName = url

        /*GlideApp.with(this)
            .asBitmap()
            .load(url)
            .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.RESOURCE))
            .listener(object: RequestListener<Bitmap>{
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Bitmap>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: Bitmap?,
                    model: Any?,
                    target: Target<Bitmap>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    startPostponedEnterTransition()
                    return false
                }

            })
            .into(viewDataBinding.missionDetailImage)*/
        GlideApp.with(this).load(url).into(viewDataBinding.missionDetailImage)

        /*GlideApp.with(this)
            .`as`(PictureDrawable::class.java)
            .transition(DrawableTransitionOptions.withCrossFade())
            .listener(SvgSoftwareLayerSetter())
            .load(url).into(viewDataBinding.missionDetailImage)*/

        postponeEnterTransition()
        viewModel.getMissionDetailInformation(SharedPreferenceManager.token, categoryId)
        startPostponedEnterTransition()
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
            supportStartPostponedEnterTransition()
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
