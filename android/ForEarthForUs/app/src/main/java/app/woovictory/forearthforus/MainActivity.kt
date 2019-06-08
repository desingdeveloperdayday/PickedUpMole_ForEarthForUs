package app.woovictory.forearthforus

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import app.woovictory.forearthforus.base.BaseActivity
import app.woovictory.forearthforus.base.BaseViewModel
import app.woovictory.forearthforus.databinding.ActivityMainBinding
import app.woovictory.forearthforus.view.article.ArticleFragment
import app.woovictory.forearthforus.view.main.MainFragment
import app.woovictory.forearthforus.view.category.MissionCategoryFragment
import app.woovictory.forearthforus.view.mypage.MyPageFragment
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<ActivityMainBinding, BaseViewModel>() {
    override val viewModel: BaseViewModel = BaseViewModel()

    override val layoutResourceId: Int
        get() = R.layout.activity_main


    private var selectedItem: Int = 0
    private val TAG = javaClass.`package`?.name.toString()
    private val mainFragment: Fragment = MainFragment.newInstance() as Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initStartView()
    }

    private fun addFragmentBasedOnId(itemId: Int) {
        selectedItem = itemId
        when (itemId) {
            R.id.navigation_main -> {
                changeFragment(mainFragment)
            }
            R.id.navigation_mission -> {
                changeFragment(MissionCategoryFragment.newInstance()!!)
            }
            R.id.navigation_article -> {
                changeFragment(ArticleFragment.newInstance())
            }
            R.id.navigation_my -> {
                changeFragment(MyPageFragment.newInstance())
            }
            else -> {
                Toast.makeText(this, "home", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun initStartView() {
        changeFragment(MainFragment.newInstance()!!)
        mainBottomNavigation.selectedItemId = R.id.navigation_main

        mainBottomNavigation.itemIconTintList = null
        // bottom navigation ripple 투명으로 변경.
        mainBottomNavigation.itemRippleColor = ContextCompat.getColorStateList(this, R.color.fefu_transparent)
        mainBottomNavigation.setOnNavigationItemSelectedListener { item: MenuItem ->
            addFragmentBasedOnId(item.itemId)
            true
        }
        disableShiftMode(mainBottomNavigation)
    }

    override fun initDataBinding() {

    }

    private fun changeFragment(fragment: Fragment) {
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.mainFrameContainer, fragment).commit()
    }

    @SuppressLint("RestrictedApi")
    private fun disableShiftMode(view: BottomNavigationView) {
        val menuView = view.getChildAt(0) as BottomNavigationMenuView
        try {
            val shiftingMode = menuView.javaClass.getDeclaredField("mShiftingMode")
            shiftingMode.isAccessible = true
            shiftingMode.setBoolean(menuView, false)
            shiftingMode.isAccessible = false
            for (i in 0 until menuView.childCount) {
                val item = menuView.getChildAt(i) as BottomNavigationItemView

                item.setShifting(false)
                // set once again checked value, so view will be updated

                item.setChecked(item.itemData.isChecked)
            }
        } catch (e: Exception) {
            Log.e(TAG, e.message)
        } catch (e: IllegalAccessException) {
            Log.e(TAG, e.message)
        }
    }
}
