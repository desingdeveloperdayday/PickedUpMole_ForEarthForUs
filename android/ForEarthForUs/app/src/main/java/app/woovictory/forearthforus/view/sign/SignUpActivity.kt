package app.woovictory.forearthforus.view.sign

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.lifecycle.Observer
import app.woovictory.forearthforus.MainActivity
import app.woovictory.forearthforus.R
import app.woovictory.forearthforus.base.BaseActivity
import app.woovictory.forearthforus.databinding.ActivitySignUpBinding
import app.woovictory.forearthforus.vm.SignUpViewModel
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpActivity : BaseActivity<ActivitySignUpBinding, SignUpViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_sign_up
    override val viewModel: SignUpViewModel by viewModel()

    var emailFlag: Boolean = false
    var nameFlag: Boolean = false
    var passwordFlag: Boolean = false
    var passwordCheckFlag: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpToolbar()
        checkEditText()
        initStartView()
        initDataBinding()
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
                        Log.v("996655", "after로?")
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
                    Log.v("996655 nf", "$nameFlag")
                    checkAllInfo()
                }

            })

            signEmailEt.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(e: Editable?) {
                    if (e.toString().isEmpty()) {
                        Log.v("996655", "2 after로?")
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
                    Log.v("996655 ef", "$emailFlag")
                    checkAllInfo()
                }

            })

            signPasswordEt.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(e: Editable?) {
                    if (e.toString().isEmpty()) {
                        Log.v("996655", "3 after로?")
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
                    Log.v("996655 pf", "$passwordFlag")
                    checkAllInfo()
                }

            })

            signPasswordCheckEt.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(e: Editable?) {
                    if (e.toString().isEmpty()) {
                        Log.v("996655", "4 after로?")
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
        if (emailFlag && nameFlag && passwordFlag && passwordCheckFlag) {
            Log.v("996655", "$emailFlag, $nameFlag, $passwordCheckFlag, $passwordFlag")
            signButton.isSelected = true
        } else {
            signButton.isSelected = false
        }
    }

    override fun initStartView() {
        viewDataBinding.apply {
            vm = viewModel
            lifecycleOwner = this@SignUpActivity
        }
    }

    override fun initDataBinding() {
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
                startActivity<MainActivity>()
                finish()
            }
        })
    }

}
