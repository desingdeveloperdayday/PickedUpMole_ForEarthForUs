package app.woovictory.forearthforus.view.mypage

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import app.woovictory.forearthforus.R
import kotlinx.android.synthetic.main.activity_alarm.*

class AlarmActivity : AppCompatActivity() {


    private var flag: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm)

        // TODO 애니메이션 구현해야 함.
        alarmSwitch.setOnCheckedChangeListener { _, isChecked ->
            setVisibility(isChecked)
        }

        alarmTimeSetLayout.setOnClickListener {
            if (!flag) {
                alarmPickerLayout.visibility = View.VISIBLE
                flag = true
            } else {
                alarmPickerLayout.visibility = View.GONE
                flag = false
            }
        }
    }

    private fun setVisibility(isChecked: Boolean) {
        if (isChecked) {
            alarmTimeSetLayout.visibility = View.VISIBLE
            //alarmPickerLayout.visibility = View.VISIBLE
        } else {
            alarmTimeSetLayout.visibility = View.GONE
            alarmPickerLayout.visibility = View.GONE
            flag = false
        }
    }
}
