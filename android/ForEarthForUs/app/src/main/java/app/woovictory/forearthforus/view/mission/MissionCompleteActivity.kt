package app.woovictory.forearthforus.view.mission

import android.os.Bundle
import android.util.Log
import android.view.View
import app.woovictory.forearthforus.R
import app.woovictory.forearthforus.base.BaseActivity
import app.woovictory.forearthforus.base.BaseViewModel
import app.woovictory.forearthforus.databinding.ActivityMissionCompleteBinding
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_mission_complete.*
import org.jetbrains.anko.startActivity
import java.util.concurrent.TimeUnit

class MissionCompleteActivity : BaseActivity<ActivityMissionCompleteBinding, BaseViewModel>(), View.OnClickListener {
    override fun onClick(v: View) {
        when (v) {
            missionCompleteBackButton -> finish()
        }
    }

    override val layoutResourceId: Int
        get() = R.layout.activity_mission_complete
    override val viewModel: BaseViewModel
        get() = BaseViewModel()

    private var id: String = ""
    private var title: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getData()
        initStartView()
        initDataBinding()
    }

    private fun getData() {
        id = intent.getStringExtra("id")
        title = intent.getStringExtra("title")
        Log.v("2103", id)
        viewDataBinding.apply {
            missionCompleteTitle.text = title
        }
    }

    override fun initStartView() {
        viewDataBinding.apply {
            missionCompleteRating.setOnRatingBarChangeListener { _, fl, _ ->
                missionCompleteScore.text = "${fl.toInt()}"+"점만큼"
                Log.v("2200123", fl.toInt().toString())

                Observable.timer(1000L, TimeUnit.MILLISECONDS)
                    .subscribe({
                        startActivity<MissionCompleteWriteActivity>(
                            "rating" to fl.toInt(),
                            "id" to id,
                            "title" to title
                        )
                    }, {

                    })
            }
        }
    }

    override fun initDataBinding() {

    }
}