package app.woovictory.forearthforus.view.account

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.Observer
import app.woovictory.forearthforus.R
import app.woovictory.forearthforus.base.BaseActivity
import app.woovictory.forearthforus.databinding.ActivitySignUpBinding
import app.woovictory.forearthforus.vm.account.SignUpViewModel
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.jetbrains.anko.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpActivity : BaseActivity<ActivitySignUpBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_sign_up
    val viewModel: SignUpViewModel by viewModel()

    var emailFlag: Boolean = false
    var nameFlag: Boolean = false
    var passwordFlag: Boolean = false
    var passwordCheckFlag: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpToolbar()
        checkEditText()
        initStartView()
        subscribeViewModel()
    }

    private fun setUpToolbar() {
        setSupportActionBar(signUpToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    private fun checkEditText() {
        viewDataBinding.apply {
            signNameEt.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(e: Editable?) {
                    if (e.toString().isEmpty()) {
                        nameFlag = false
                        checkAllInfo()
                    }
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, before: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    s?.length?.let {
                        nameFlag = it > 0
                    }
                    checkAllInfo()
                }

            })

            signEmailEt.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(e: Editable?) {
                    if (e.toString().isEmpty()) {
                        emailFlag = false
                        checkAllInfo()
                    }
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, before: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    s?.length?.let {
                        emailFlag = it > 0
                    }
                    checkAllInfo()
                }

            })

            signPasswordEt.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(e: Editable?) {
                    if (e.toString().isEmpty()) {
                        passwordFlag = false
                        checkAllInfo()
                    }
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, before: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    s?.length?.let {
                        passwordFlag = it > 0
                    }
                    checkAllInfo()
                }

            })

            signPasswordCheckEt.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(e: Editable?) {
                    if (e.toString().isEmpty()) {
                        passwordCheckFlag = false
                        checkAllInfo()
                    }
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, before: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    s?.length?.let {
                        passwordCheckFlag = it > 0
                    }
                    checkAllInfo()
                }
            })
        }
    }

    fun checkAllInfo() {
        signButton.isSelected = emailFlag && nameFlag && passwordFlag && passwordCheckFlag
    }

    override fun initStartView() {
        viewDataBinding.apply {
            vm = viewModel
            lifecycleOwner = this@SignUpActivity
        }
    }

    override fun subscribeViewModel() {
        viewModel.clickToSignUp.observe(this, Observer {
            if (viewDataBinding.signButton.isSelected) {
                viewModel.postSignUp(
                    signEmailEt.text.toString(), signNameEt.text.toString()
                    , signPasswordEt.text.toString(), signPasswordCheckEt.text.toString()
                )
            } else {
                toast(getString(R.string.text_login_alert_text))
            }
        })

        viewModel.signUpResponse.observe(this, Observer {
            if (it) {
                val intent = Intent(this, FieldSelectActivity::class.java)
                startActivity(intent)
                finish()
            }
        })
    }
}