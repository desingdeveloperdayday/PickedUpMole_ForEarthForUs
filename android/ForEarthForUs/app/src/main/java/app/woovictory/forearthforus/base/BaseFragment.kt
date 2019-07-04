package app.woovictory.forearthforus.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

/**
 * Created by VictoryWoo
 */
abstract class BaseFragment<B : ViewDataBinding, V : BaseViewModel> : Fragment() {
    lateinit var viewDataBinding: B
    abstract val layoutResourceId: Int
    abstract val viewModel: V

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewDataBinding = DataBindingUtil.inflate(inflater, layoutResourceId, container, false)
        return viewDataBinding.root
    }

    abstract fun initStartView()

    abstract fun subscribeViewModel()
}