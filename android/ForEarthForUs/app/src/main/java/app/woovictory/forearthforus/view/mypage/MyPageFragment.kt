package app.woovictory.forearthforus.view.mypage

import android.app.AlertDialog
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import app.woovictory.forearthforus.R
import app.woovictory.forearthforus.base.BaseFragment
import app.woovictory.forearthforus.databinding.FragmentMypageBinding
import app.woovictory.forearthforus.util.SharedPreferenceManager
import app.woovictory.forearthforus.view.account.LoginActivity
import app.woovictory.forearthforus.view.mypage.achieve.AchieveListActivity
import app.woovictory.forearthforus.view.mypage.alarm.AlarmActivity
import app.woovictory.forearthforus.view.mypage.scrap.ScrapListActivity
import app.woovictory.forearthforus.vm.mypage.MyPageViewModel
import org.jetbrains.anko.support.v4.alert
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.textColor
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by VictoryWoo
 */
class MyPageFragment : BaseFragment<FragmentMypageBinding, MyPageViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_mypage
    override val viewModel: MyPageViewModel by viewModel()

    companion object {
        private var fragment: MyPageFragment? = null
        fun newInstance(): MyPageFragment {
            if (fragment == null) {
                fragment = MyPageFragment()
            }
            return MyPageFragment()
        }
    }

    private val myPageViewModel: MyPageViewModel
        get() = ViewModelProviders.of(this@MyPageFragment).get(MyPageViewModel::class.java)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initStartView()
        subscribeViewModel()

        viewDataBinding.myPageMissionSuggest.setOnClickListener {
            toast("준비 중입니다.")
        }
    }

    override fun initStartView() {
        viewDataBinding.apply {
            vm = myPageViewModel
            lifecycleOwner = this@MyPageFragment
            myPageProfileUserName.text = SharedPreferenceManager.userName
            myPageProfileEmail.text = SharedPreferenceManager.userEmail
        }
    }

    override fun subscribeViewModel() {
        myPageViewModel.clickToAchieve.observe(this, Observer {
            startActivity<AchieveListActivity>()
        })

        // TODO : 로그아웃 로직을 ViewModel 에서 처리할지 View 에서 처리할지 생각해봐야 함.
        myPageViewModel.clickToLogOut.observe(this, Observer {
            alert(title = "로그아웃", message = "로그아웃을 하시겠습니까?") {
                positiveButton("예") {
                    SharedPreferenceManager.removeAllData()
                    startActivity<LoginActivity>()
                    activity?.finish()
                }
                negativeButton("아니오") {
                    it.dismiss()
                }
            }.show().apply {
                getButton(AlertDialog.BUTTON_POSITIVE)?.let { button ->
                    button.textColor = ContextCompat.getColor(context, R.color.fe_fu_main)
                }
                getButton(AlertDialog.BUTTON_NEGATIVE)?.let { button ->
                    button.textColor = ContextCompat.getColor(context, R.color.fe_fu_main)
                }
            }

        })

        myPageViewModel.clickToScrapList.observe(this, Observer {
            startActivity<ScrapListActivity>()
        })

        myPageViewModel.clickToAlarm.observe(this, Observer {
            startActivity<AlarmActivity>()
        })

        myPageViewModel.clickToProfile.observe(this, Observer {

        })
    }
}