package app.woovictory.forearthforus.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import app.woovictory.forearthforus.R
import app.woovictory.forearthforus.view.mission.MissionDetailActivity
import app.woovictory.forearthforus.view.sign.SignUpActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginButton.setOnClickListener {
            startActivity<SignUpActivity>()
            //startActivity<MissionDetailActivity>()
        }
    }
}
