package app.woovictory.forearthforus.view.mission

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.databinding.ViewDataBinding
import app.woovictory.forearthforus.R
import app.woovictory.forearthforus.base.BaseActivity
import app.woovictory.forearthforus.base.BaseViewModel
import app.woovictory.forearthforus.databinding.ActivityMissionCompleteBinding
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_mission_complete.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import java.util.concurrent.TimeUnit

class MissionCompleteActivity : BaseActivity<ActivityMissionCompleteBinding, BaseViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_mission_complete
    override val viewModel: BaseViewModel
        get() = BaseViewModel()

    var id: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getData()
        initStartView()
        initDataBinding()
    }

    private fun getData() {
        id = intent.getStringExtra("id")
        Log.v("2103", id)
    }

    override fun initStartView() {

        viewDataBinding.apply {
            missionCompleteRating.setOnRatingBarChangeListener { _, fl, _ ->
                missionCompleteScore.text = "${fl.toInt()} 점만큼"
                Log.v("2200123", fl.toInt().toString())

                Observable.timer(500L, TimeUnit.MILLISECONDS)
                    .subscribe({
                        startActivity<MissionCompleteWriteActivity>(
                            "rating" to fl.toInt(),
                            "id" to id
                        )
                    }, {

                    })
            }
        }

    }

    override fun initDataBinding() {

    }

    private fun checkCompleteRatingState(count: Int) {
        /*when (count) {
            1 -> {
                viewDataBinding.apply {

                    // 다른 것들이 선택되어 있는 상태에서 1을 선택한 경우.
                    if (missionCompleteRating2.isSelected || missionCompleteRating3.isSelected ||
                        missionCompleteRating4.isSelected || missionCompleteRating5.isSelected
                    ) {
                        missionCompleteRating2.isSelected = false
                        missionCompleteRating3.isSelected = false
                        missionCompleteRating4.isSelected = false
                        missionCompleteRating5.isSelected = false
                    } else {
                        // 아무것도 선택하지 않고 1만 선택한 경우.
                        missionCompleteRating1.isSelected = !missionCompleteRating1.isSelected
                    }
                    startActivity<MissionCompleteWriteActivity>("id" to id)

                }
            }
            2 -> {
                viewDataBinding.apply {
                    // 아무것도 선택하지 않고 1만 선택한 경우.

                    *//*if(missionCompleteRating1.isSelected and  missionCompleteRating2.isSelected){
                        missionCompleteRating1.isSelected = false
                        missionCompleteRating2.isSelected = false
                    }*//*
                    // 1이 이미 선택된 상태에서 누르면 2만 선택된 상태로 바꾼다.
                    if (missionCompleteRating1.isSelected) {
                        missionCompleteRating2.isSelected = !missionCompleteRating2.isSelected

                    } else if (missionCompleteRating3.isSelected ||
                        missionCompleteRating4.isSelected || missionCompleteRating5.isSelected
                    ) {
                        //missionCompleteRating1.isSelected = false
                        missionCompleteRating3.isSelected = false
                        missionCompleteRating4.isSelected = false
                        missionCompleteRating5.isSelected = false
                    } else {
                        // 아무것도 선택하지 2만 선택한 경우.
                        missionCompleteRating1.isSelected = !missionCompleteRating1.isSelected
                        missionCompleteRating2.isSelected = !missionCompleteRating2.isSelected
                    }
                }
            }
            5 -> {
                viewDataBinding.apply {
                    missionCompleteRating1.isSelected = !missionCompleteRating1.isSelected
                    missionCompleteRating2.isSelected = !missionCompleteRating2.isSelected
                    missionCompleteRating3.isSelected = !missionCompleteRating3.isSelected
                    missionCompleteRating4.isSelected = !missionCompleteRating4.isSelected
                    missionCompleteRating5.isSelected = !missionCompleteRating5.isSelected
                }
            }
            4 -> {
                viewDataBinding.apply {
                    if (missionCompleteRating5.isSelected) {
                        missionCompleteRating5.isSelected = !missionCompleteRating5.isSelected
                    } else {
                        missionCompleteRating1.isSelected = !missionCompleteRating1.isSelected
                        missionCompleteRating2.isSelected = !missionCompleteRating2.isSelected
                        missionCompleteRating3.isSelected = !missionCompleteRating3.isSelected
                        missionCompleteRating4.isSelected = !missionCompleteRating4.isSelected
                    }

                }
            }
        }*/
    }
}
