package app.woovictory.forearthforus.view.mypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import app.woovictory.forearthforus.R
import app.woovictory.forearthforus.databinding.FragmentMypageBinding
import app.woovictory.forearthforus.vm.MyPageViewModel
import org.jetbrains.anko.support.v4.toast

/**
 * Created by VictoryWoo
 */
class MyPageFragment : Fragment() {

    companion object {
        //private var fragment: MyPageFragment? = null
        fun newInstance(): MyPageFragment {
            return MyPageFragment()
        }
    }

    private lateinit var fragmentMyPageDataBinding: FragmentMypageBinding
    var myPageViewModel: MyPageViewModel = MyPageViewModel()

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
    }

    private fun setViewModel() {
        fragmentMyPageDataBinding.apply {
            viewModel = myPageViewModel
            lifecycleOwner = this@MyPageFragment
        }
    }

    private fun initDataBinding() {
        myPageViewModel.clickToAchieve.observe(this, Observer {
            toast("아아아아")
            //myPageViewModel.clickToAchieve()
        })
    }
}