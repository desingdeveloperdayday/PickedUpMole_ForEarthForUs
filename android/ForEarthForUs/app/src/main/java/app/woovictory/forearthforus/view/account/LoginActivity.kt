package app.woovictory.forearthforus.view.account

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.Observer
import app.woovictory.forearthforus.MainActivity
import app.woovictory.forearthforus.R
import app.woovictory.forearthforus.base.BaseActivity
import app.woovictory.forearthforus.databinding.ActivityLoginBinding
import app.woovictory.forearthforus.vm.account.LoginViewModel
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : BaseActivity<ActivityLoginBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_login
    private val viewModel: LoginViewModel by viewModel()

    private var loginEmailFlag: Boolean = false
    private var loginPasswordFlag: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkEditText()
        initStartView()
        subscribeViewModel()
    }

    private fun checkEditText() {
        viewDataBinding.apply {
            loginEmailEt.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(e: Editable?) {
                    if (e.toString().isEmpty()) {
                        loginEmailFlag = false
                        checkAllInformation()
                    }
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, before: Int) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    s?.length?.let { length ->
                        loginEmailFlag = length > 0
                    }
                    checkAllInformation()
                }
            })

            loginPasswordEt.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(e: Editable?) {
                    if (e.toString().isEmpty()) {
                        loginPasswordFlag = false
                        checkAllInformation()
                    }
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, before: Int) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    s?.length?.let { length ->
                        loginPasswordFlag = length > 0
                        checkAllInformation()
                    }
                }
            })
        }
    }

    private fun checkAllInformation() {
        viewDataBinding.loginButton.isSelected = loginEmailFlag && loginPasswordFlag
    }

    override fun initStartView() {
        viewDataBinding.apply {
            vm = viewModel
            lifecycleOwner = this@LoginActivity
        }
    }

    override fun subscribeViewModel() {
        viewModel.clickToLogin.observe(this, Observer {
            with(viewDataBinding) {
                if (loginButton.isSelected) {
                    val email = loginEmailEt.text.toString()
                    val password = loginPasswordEt.text.toString()
                    viewModel.postLogin(email, password)
                } else {
                    toast(getString(R.string.text_login_alert_text))
                }
            }
        })

        viewModel.loginResponse.observe(this, Observer {
            if (it) {
                goToMainActivity()
            } else {
                toast("정보를 확인해주세요.")
            }
        })

        viewModel.clickToSignActivity.observe(this, Observer {
            startActivity<SignUpActivity>()
            finish()
        })
    }

    private fun goToMainActivity() {
        startActivity<MainActivity>()
        finish()
    }
}
