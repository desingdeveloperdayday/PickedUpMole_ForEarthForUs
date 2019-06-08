package app.woovictory.forearthforus.view.account

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.lifecycle.Observer
import app.woovictory.forearthforus.MainActivity
import app.woovictory.forearthforus.R
import app.woovictory.forearthforus.base.BaseActivity
import app.woovictory.forearthforus.databinding.ActivityLoginBinding
import app.woovictory.forearthforus.util.SharedPreferenceManager
import app.woovictory.forearthforus.vm.account.LoginViewModel
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_login
    override val viewModel: LoginViewModel by viewModel()
    private val tag = javaClass.simpleName

    var loginEmailFlag: Boolean = false
    var loginPasswordFlag: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkSavedToken()
        checkEditText()
        initStartView()
        initDataBinding()
    }

    private fun checkSavedToken() {
        if (SharedPreferenceManager.token.isNotBlank()) {
            Log.d(tag + 19992, "token exist")
            Log.d(tag + 19992, SharedPreferenceManager.token.isBlank().toString())

            goToMainActivity()
        } else {
            Log.d(tag + 19992, "No token")
        }
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

    override fun initDataBinding() {
        viewModel.clickToLogin.observe(this, Observer {
            if (viewDataBinding.loginButton.isSelected) {
                viewModel.postLogin("dudogi@naver.com", "1q2w3e4r!!")
            } else {
                toast(getString(R.string.text_login_alert_text))
            }
        })

        viewModel.loginResponse.observe(this, Observer {
            if (it) {
                goToMainActivity()
            }
        })
        viewModel.clickToGoogleLogin.observe(this, Observer {
            toast("Google login 준비중..")
        })
        viewModel.clickToSignActivity.observe(this, Observer {
            startActivity<SignUpActivity>()
        })
    }

    private fun goToMainActivity() {
        //startActivity<FieldSelectActivity>()
        startActivity<MainActivity>()
        finish()
    }
}