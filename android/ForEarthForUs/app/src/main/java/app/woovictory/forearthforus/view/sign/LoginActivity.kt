package app.woovictory.forearthforus.view.sign

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import app.woovictory.forearthforus.R
import app.woovictory.forearthforus.base.BaseActivity
import app.woovictory.forearthforus.databinding.ActivityLoginBinding
import app.woovictory.forearthforus.view.mission.MissionDetailActivity
import app.woovictory.forearthforus.view.sign.SignUpActivity
import app.woovictory.forearthforus.vm.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity

class LoginActivity : BaseActivity<ActivityLoginBinding,LoginViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_main
    override val viewModel: LoginViewModel = LoginViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginButton.setOnClickListener {
            startActivity<SignUpActivity>()
            //startActivity<MissionDetailActivity>()
        }
    }

    override fun initStartView() {

    }

    override fun initDataBinding() {

    }
}
