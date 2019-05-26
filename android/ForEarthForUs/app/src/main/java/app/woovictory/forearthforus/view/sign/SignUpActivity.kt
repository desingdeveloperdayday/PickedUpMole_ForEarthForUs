package app.woovictory.forearthforus.view.sign

import android.os.Bundle
import app.woovictory.forearthforus.MainActivity
import app.woovictory.forearthforus.R
import app.woovictory.forearthforus.base.BaseActivity
import app.woovictory.forearthforus.databinding.ActivitySignUpBinding
import app.woovictory.forearthforus.vm.SignUpViewModel
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.jetbrains.anko.startActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpActivity : BaseActivity<ActivitySignUpBinding, SignUpViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_sign_up
    override val viewModel: SignUpViewModel by viewModel()

    override fun initStartView() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initDataBinding() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        initToolbar()
        signButton.setOnClickListener {
            startActivity<MainActivity>()
        }

    }

    private fun initToolbar() {
        setSupportActionBar(signUpToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }
}
