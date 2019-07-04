package app.woovictory.forearthforus.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * Created by VictoryWoo
 */
abstract class BaseActivity<B : ViewDataBinding> : AppCompatActivity() {

    lateinit var viewDataBinding: B
    abstract val layoutResourceId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding = DataBindingUtil.setContentView(this, layoutResourceId)
    }

    // view 초기화 작업
    abstract fun initStartView()

    // 데이터 바인딩을 통해 observe 할 때 주로 사용.
    abstract fun initDataBinding()
}