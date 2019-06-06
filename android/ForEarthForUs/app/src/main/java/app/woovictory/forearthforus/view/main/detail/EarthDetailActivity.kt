package app.woovictory.forearthforus.view.main.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import app.woovictory.forearthforus.R
import kotlinx.android.synthetic.main.activity_earth_detail.*

@Suppress("CAST_NEVER_SUCCEEDS")
class EarthDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_earth_detail)
        //setUpToolbar()
    }

  /*  private fun setUpToolbar() {
        setSupportActionBar(earthDetailToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }*/
}
