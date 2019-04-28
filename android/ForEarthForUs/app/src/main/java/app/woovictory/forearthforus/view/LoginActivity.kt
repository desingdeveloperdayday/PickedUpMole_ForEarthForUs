package app.woovictory.forearthforus.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import app.woovictory.forearthforus.R
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginButton.setOnClickListener {
            startActivity<SignUpActivity>()
        }
    }
}
