package app.woovictory.forearthforus.view.sign

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import app.woovictory.forearthforus.MainActivity
import app.woovictory.forearthforus.R
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.jetbrains.anko.startActivity

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        initToolbar()
        signButton.setOnClickListener {
            startActivity<MainActivity>()
        }

    }

    private fun initToolbar(){
        setSupportActionBar(signUpToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }
}
