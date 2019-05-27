package app.woovictory.forearthforus.view.sign

import android.os.Bundle
import androidx.lifecycle.Observer
import app.woovictory.forearthforus.MainActivity
import app.woovictory.forearthforus.R
import app.woovictory.forearthforus.base.BaseActivity
import app.woovictory.forearthforus.databinding.ActivityLoginBinding
import app.woovictory.forearthforus.util.SharedPreferenceManager
import app.woovictory.forearthforus.vm.LoginViewModel
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_login
    override val viewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initStartView()
        initDataBinding()

        if (SharedPreferenceManager.token!!.isNotEmpty()) {
            startActivity<MainActivity>()
            finish()
        }
    }

    override fun initStartView() {
        viewDataBinding.apply {
            vm = viewModel
            lifecycleOwner = this@LoginActivity
        }
    }

    override fun initDataBinding() {
        viewModel.clickToLogin.observe(this, Observer {
            toast("로그인 버튼")
            viewModel.postLogin("dudogi@naver.com", "1q2w3e4r!!")
        })
        viewModel.clickToGoogleLogin.observe(this, Observer {
            toast("Google login 준비중..")
        })
        viewModel.clickToSignActivity.observe(this, Observer {
            startActivity<SignUpActivity>()
        })
    }
}
