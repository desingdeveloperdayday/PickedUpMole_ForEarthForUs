package app.woovictory.forearthforus.view.mission

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import app.woovictory.forearthforus.R
import app.woovictory.forearthforus.base.BaseActivity
import app.woovictory.forearthforus.databinding.ActivityMissionCompleteWriteBinding
import app.woovictory.forearthforus.model.mission.MissionFeedCompleteRequest
import app.woovictory.forearthforus.util.CustomDialog
import app.woovictory.forearthforus.util.SharedPreferenceManager
import app.woovictory.forearthforus.vm.mission.MissionFeedCompleteWriteViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MissionCompleteWriteActivity :
    BaseActivity<ActivityMissionCompleteWriteBinding, MissionFeedCompleteWriteViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_mission_complete_write
    override val viewModel: MissionFeedCompleteWriteViewModel by viewModel()

    private var id: String = ""
    private var rating: Int = 0
    private var title: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getData()
        initStartView()
        initDataBinding()
    }

    private fun getData() {
        id = intent.getStringExtra("id")
        rating = intent.getIntExtra("rating", 0)
        title = intent.getStringExtra("title")
        Log.v("2103001", id)
        Log.v("2103001", rating.toString())
        Log.v("2103001", title)
    }

    override fun initStartView() {
        viewDataBinding.apply {
            vm = viewModel
            lifecycleOwner = this@MissionCompleteWriteActivity
            missionCompleteWriteTitle.text = title
            missionCompleteWriteScore.text = "$rating" + "점만큼"
            missionCompleteRating.rating = rating.toFloat()
        }
    }

    override fun initDataBinding() {
        viewModel.clickToBackMission.observe(this, Observer {
            finish()
        })

        val missionFeedCompleteRequest = MissionFeedCompleteRequest(5, "Good")

        viewModel.clickToComplete.observe(this, Observer {
            viewModel.completeMission(SharedPreferenceManager.token, missionFeedCompleteRequest, id)
        })

        viewModel.missionFeedCompleteResponse.observe(this, Observer {
            /*val ma = MainActivity()
            ma.refreshFragment()*/
            val dialog = CustomDialog(this@MissionCompleteWriteActivity)
            dialog.setCanceledOnTouchOutside(false)
            dialog.show()
        })
    }
}
