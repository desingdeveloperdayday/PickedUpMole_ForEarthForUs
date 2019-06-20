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
import app.woovictory.forearthforus.view.category.MissionCategoryFragment
import app.woovictory.forearthforus.view.main.MainFragment
import app.woovictory.forearthforus.view.mypage.MyPageFragment
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<ActivityMainBinding, BaseViewModel>() {
    override val viewModel: BaseViewModel = BaseViewModel()

    override val layoutResourceId: Int
        get() = R.layout.activity_main


    val fragment1: Fragment = MainFragment.newInstance()
    val fragment2: Fragment = MissionCategoryFragment.newInstance()
    val fragment3: Fragment = ArticleFragment.newInstance()
    val fragment4: Fragment = MyPageFragment.newInstance()
    val fm = supportFragmentManager
    var active = fragment1

    private var selectedItem: Int = 0
    private val TAG = javaClass.`package`?.name.toString()
    private val mainFragment: Fragment = MainFragment.newInstance()
    private val missionCategoryFragment: Fragment = MissionCategoryFragment.newInstance()
    private val articleFragment: Fragment = ArticleFragment.newInstance()
    private val myPageFragment: Fragment = MyPageFragment.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initStartView()
        fm.beginTransaction().add(R.id.mainFrameContainer, fragment4, "4").hide(fragment4).commit()
        fm.beginTransaction().add(R.id.mainFrameContainer, fragment3, "3").hide(fragment3).commit()
        fm.beginTransaction().add(R.id.mainFrameContainer, fragment2, "2").hide(fragment2).commit()
        fm.beginTransaction().add(R.id.mainFrameContainer, fragment1, "1").commit()
    }

    private fun addFragmentBasedOnId(itemId: Int) {
        selectedItem = itemId
        when (itemId) {
            R.id.navigation_main -> {
                fm.beginTransaction().hide(active).show(fragment1).commit()
                active = fragment1
                //changeFragment(mainFragment)
            }
            R.id.navigation_mission -> {
                fm.beginTransaction().hide(active).show(fragment2).commit()
                active = fragment2
                //changeFragment(missionCategoryFragment)
            }
            R.id.navigation_article -> {
                fm.beginTransaction().hide(active).show(fragment3).commit()
                active = fragment3
                //changeFragment(articleFragment)
            }
            R.id.navigation_my -> {
                fm.beginTransaction().hide(active).show(fragment4).commit()
                active = fragment4
                //changeFragment(myPageFragment)
            }
            else -> {
                Toast.makeText(this, "home", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun initStartView() {
        changeFragment(MainFragment.newInstance())
        mainBottomNavigation.selectedItemId = R.id.navigation_main

        mainBottomNavigation.itemIconTintList = null
        // bottom navigation ripple 투명으로 변경.
        mainBottomNavigation.itemRippleColor = ContextCompat.getColorStateList(this, R.color.fe_fu_transparent)
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
