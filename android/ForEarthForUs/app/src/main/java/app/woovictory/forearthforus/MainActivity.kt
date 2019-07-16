package app.woovictory.forearthforus

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import app.woovictory.forearthforus.base.BaseActivity
import app.woovictory.forearthforus.databinding.ActivityMainBinding
import app.woovictory.forearthforus.util.TAG
import app.woovictory.forearthforus.util.TIME_INTERVAL
import app.woovictory.forearthforus.view.article.ArticleFragment
import app.woovictory.forearthforus.view.category.MissionCategoryFragment
import app.woovictory.forearthforus.view.main.MainFragment
import app.woovictory.forearthforus.view.main.detail.EarthDetailActivity
import app.woovictory.forearthforus.view.mypage.MyPageFragment
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_main


    private val fm: FragmentManager = supportFragmentManager
    private var backPressedTime: Long = 0
    private val mainFragment: Fragment = MainFragment.newInstance()
    private val missionCategoryFragment: Fragment = MissionCategoryFragment.newInstance()
    private val articleFragment: Fragment = ArticleFragment.newInstance()
    private val myPageFragment: Fragment = MyPageFragment.newInstance()
    private var active: Fragment = mainFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initStartView()
        subscribeViewModel()
    }

    override fun onBackPressed() {

        // 뒤로 버튼 클릭 시간과 현재 시간을 비교하여 계산한다.

        // 마지막 '뒤로' 버튼 클릭 시간이 이전 '뒤로' 버튼 클릭 시간과의 차이가
        // TIME_INTERVAL(여기서 2초)보다 클 때는 토스트 메시지만 띄운다.
        // System.currentTimeMillis() : 마지막 뒤로 버튼 클릭 시간을 의미
        // backPressedTime + TIME_INTERVAL : 이전 뒤로 버튼 클릭 시간을 의미
        // 이전에 누른 적이 없기 때문에 backPressedTime 은 0이다.
        if (System.currentTimeMillis() > backPressedTime + TIME_INTERVAL) {
            backPressedTime = System.currentTimeMillis()
            toast("\'뒤로\' 버튼을 한번 더 누르시면 종료됩니다.")
        } else {
            // 마지막 '뒤로' 버튼 클릭 시간이 이전 '뒤로' 버튼 클릭 시간과의 차이가
            // 2초 보다 작을 때 화면을 종료시킨다.
            finish()
        }

    }

    private fun addFragmentBasedOnId(itemId: Int) {
        when (itemId) {
            R.id.navigation_main -> {
                changeFragment(active, mainFragment)
            }
            R.id.navigation_mission -> {
                changeFragment(active, missionCategoryFragment)
            }
            R.id.navigation_article -> {
                changeFragment(active, articleFragment)
            }
            R.id.navigation_my -> {
                changeFragment(active, myPageFragment)
            }
            else -> {
                Toast.makeText(this, "home", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun changeFragment(activeFragment: Fragment, fragment: Fragment) {
        fm.beginTransaction().hide(activeFragment).show(fragment).commit()
        active = fragment
    }

    // 미션 탭 대신 클릭.
    fun replaceFragment() {
        fm.beginTransaction().hide(active).show(missionCategoryFragment).commit()
        active = missionCategoryFragment
        mainBottomNavigation.selectedItemId = R.id.navigation_mission
    }

    override fun initStartView() {
        fm.beginTransaction().add(R.id.mainFrameContainer, myPageFragment, "4").hide(myPageFragment).commit()
        fm.beginTransaction().add(R.id.mainFrameContainer, articleFragment, "3").hide(articleFragment).commit()
        fm.beginTransaction().add(R.id.mainFrameContainer, missionCategoryFragment, "2").hide(missionCategoryFragment)
            .commit()
        fm.beginTransaction().add(R.id.mainFrameContainer, mainFragment, "1").commit()

        active = mainFragment

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

    override fun subscribeViewModel() {

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