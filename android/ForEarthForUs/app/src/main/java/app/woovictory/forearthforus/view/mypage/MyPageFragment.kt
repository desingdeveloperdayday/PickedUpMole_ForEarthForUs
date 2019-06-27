package app.woovictory.forearthforus.view.mypage

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import app.woovictory.forearthforus.R
import app.woovictory.forearthforus.databinding.FragmentMypageBinding
import app.woovictory.forearthforus.util.CustomDialog
import app.woovictory.forearthforus.util.SharedPreferenceManager
import app.woovictory.forearthforus.view.account.LoginActivity
import app.woovictory.forearthforus.view.mypage.achieve.AchieveListActivity
import app.woovictory.forearthforus.vm.mypage.MyPageViewModel
import org.jetbrains.anko.support.v4.alert
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.textColor

/**
 * Created by VictoryWoo
 */
class MyPageFragment : Fragment() {

    companion object {
        private var fragment: MyPageFragment? = null
        fun newInstance(): MyPageFragment {
            if (fragment == null) {
                fragment = MyPageFragment()
            }

            return MyPageFragment()
        }
    }

    private lateinit var fragmentMyPageDataBinding: FragmentMypageBinding
    private val myPageViewModel: MyPageViewModel
        get() = ViewModelProviders.of(this@MyPageFragment).get(MyPageViewModel::class.java)


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentMyPageDataBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_mypage, container,
            false
        )
        return fragmentMyPageDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setViewModel()
        initDataBinding()

        fragmentMyPageDataBinding.myPageMissionSuggest.setOnClickListener {
            toast(requireContext().toString())
            val dialog = CustomDialog(requireContext())
            dialog.setCanceledOnTouchOutside(false)
            dialog.show()
        }


    }

    private fun setViewModel() {
        fragmentMyPageDataBinding.apply {
            vm = myPageViewModel
            lifecycleOwner = this@MyPageFragment
        }
    }

    private fun initDataBinding() {
        myPageViewModel.clickToAchieve.observe(this, Observer {
            startActivity<AchieveListActivity>()


            /*CustomDialog.CustomBuilder(activity!!.applicationContext)
                .setTitle("지역 농산물 이용하기 미션 성공!")
                .setContent("일상 속에서 그린라이프를 실천해주다니!\n" +
                        "우리를 위해 노력해줘서 고마워요.")
                .show()*/

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
                    button.textColor = Color.BLACK
                }
                getButton(AlertDialog.BUTTON_NEGATIVE)?.let { button ->
                    button.textColor = Color.BLACK
                }
            }

        })

        myPageViewModel.clickToScrapList.observe(this, Observer {
            startActivity<ScrapListActivity>()
        })

        myPageViewModel.clickToAlarm.observe(this, Observer {
            startActivity<AlarmActivity>()
        })

        fragmentMyPageDataBinding.apply {
            myPageProfileUserName.text = SharedPreferenceManager.userName
            myPageProfileEmail.text = SharedPreferenceManager.userEmail
        }
    }
}