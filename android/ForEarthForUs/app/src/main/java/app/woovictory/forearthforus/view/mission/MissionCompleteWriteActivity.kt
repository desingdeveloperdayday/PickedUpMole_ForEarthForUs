package app.woovictory.forearthforus.view.mission

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.lifecycle.Observer
import app.woovictory.forearthforus.R
import app.woovictory.forearthforus.base.BaseActivity
import app.woovictory.forearthforus.databinding.ActivityMissionCompleteWriteBinding
import app.woovictory.forearthforus.model.mission.MissionFeedCompleteRequest
import app.woovictory.forearthforus.util.CustomDialog
import app.woovictory.forearthforus.util.SharedPreferenceManager
import app.woovictory.forearthforus.util.glide.GlideApp
import app.woovictory.forearthforus.vm.mission.MissionFeedCompleteWriteViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MissionCompleteWriteActivity :
    BaseActivity<ActivityMissionCompleteWriteBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_mission_complete_write
    val viewModel: MissionFeedCompleteWriteViewModel by viewModel()

    private var feedId: String = ""
    private var rating: Int = 0
    private var title: String = ""
    private var imageUrl: String = ""
    private var category: Int = 0
    private var completeMessage: String = ""
    private var editTextFlag = false
    private var message: String = ""
    private lateinit var missionFeedCompleteRequest: MissionFeedCompleteRequest
    private var editTextListener = object : TextWatcher {
        override fun afterTextChanged(e: Editable?) {
            if (e.toString().isEmpty()) {
                editTextFlag = false
                Log.v("887712 after", editTextFlag.toString())
                isActiveEditText()
            }
            missionFeedCompleteRequest.message = e.toString()
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, before: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            s?.length?.let {
                Log.v("887712 onChanged", it.toString())
                editTextFlag = it > 0
                isActiveEditText()
            }
        }
    }

    private fun isActiveEditText() {
        viewDataBinding.missionComplete.isSelected = editTextFlag
        Log.v("887712 isActive", editTextFlag.toString())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getData()
        initStartView()
        subscribeViewModel()
    }

    private fun getData() {
        feedId = intent.getStringExtra("feedId")
        rating = intent.getIntExtra("rating", 0)
        title = intent.getStringExtra("title")
        imageUrl = intent.getStringExtra("imageUrl")
        category = intent.getIntExtra("category", 0)
        completeMessage = intent.getStringExtra("completeMessage")
        Log.v("2103001 feedId", feedId)
        Log.v("2103001 rating", rating.toString())
        Log.v("2103001 title", title)
        Log.v("2103001 imageUrl", imageUrl)
        Log.v("2103001 category", category.toString())
    }

    override fun initStartView() {
        viewDataBinding.apply {
            vm = viewModel
            lifecycleOwner = this@MissionCompleteWriteActivity
            missionCompleteEt.addTextChangedListener(editTextListener)
            missionCompleteWriteTitle.text = title
            missionCompleteWriteScore.text = "$rating" + "점만큼"
            missionCompleteRating.rating = rating.toFloat()
            GlideApp.with(missionCompleteWriteImage.context)
                .load(imageUrl)
                .into(missionCompleteWriteImage)
        }
        missionFeedCompleteRequest = MissionFeedCompleteRequest(rating, message)
    }

    override fun subscribeViewModel() {
        viewModel.clickToBackMission.observe(this, Observer {
            finish()
        })


        missionFeedCompleteRequest.result = rating

        viewModel.clickToComplete.observe(this, Observer {
            viewModel.completeMission(SharedPreferenceManager.token, missionFeedCompleteRequest, feedId)
        })

        viewModel.missionFeedCompleteResponse.observe(this, Observer {
            val dialog = CustomDialog(this@MissionCompleteWriteActivity, category, title, completeMessage)
            dialog.setCanceledOnTouchOutside(false)
            dialog.show()
        })
    }
}
