package app.woovictory.forearthforus.view.mission

import android.os.Bundle
import android.util.Log
import android.view.View
import app.woovictory.forearthforus.R
import app.woovictory.forearthforus.base.BaseActivity
import app.woovictory.forearthforus.databinding.ActivityMissionCompleteBinding
import app.woovictory.forearthforus.util.glide.GlideApp
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_mission_complete.*
import org.jetbrains.anko.startActivity
import java.util.concurrent.TimeUnit

class MissionCompleteActivity : BaseActivity<ActivityMissionCompleteBinding>(), View.OnClickListener {
    override fun onClick(v: View) {
        when (v) {
            missionCompleteBackButton -> finish()
        }
    }

    override val layoutResourceId: Int
        get() = R.layout.activity_mission_complete

    private var feedId: String = ""
    private var title: String = ""
    private var imageUrl: String = ""
    private var completeMessage: String = ""
    private var category: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getData()
        initStartView()
        subscribeViewModel()
    }

    private fun getData() {
        feedId = intent.getStringExtra("feedId")
        title = intent.getStringExtra("title")
        imageUrl = intent.getStringExtra("imageUrl")
        category = intent.getIntExtra("category", 0)
        completeMessage = intent.getStringExtra("completeMessage")

        viewDataBinding.apply {
            missionCompleteTitle.text = title
            GlideApp.with(missionCompleteImage.context).load(imageUrl).into(missionCompleteImage)
        }
    }

    override fun initStartView() {
        viewDataBinding.apply {
            missionCompleteRating.setOnRatingBarChangeListener { _, fl, _ ->
                missionCompleteScore.text = "${fl.toInt()}" + "점만큼"
                Log.v("2200123", fl.toInt().toString())

                Observable.timer(1000L, TimeUnit.MILLISECONDS)
                    .subscribe({
                        startActivity<MissionCompleteWriteActivity>(
                            "rating" to fl.toInt(),
                            "feedId" to feedId,
                            "title" to title,
                            "imageUrl" to imageUrl,
                            "category" to category,
                            "completeMessage" to completeMessage
                        )
                    }, {

                    })
            }
            missionCompleteBackButton.setOnClickListener(this@MissionCompleteActivity)
        }
    }

    override fun subscribeViewModel() {

    }
}