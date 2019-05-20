package app.woovictory.forearthforus

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import app.woovictory.forearthforus.base.BaseActivity
import app.woovictory.forearthforus.databinding.ActivityMainBinding
import app.woovictory.forearthforus.view.main.MainFragment
import app.woovictory.forearthforus.view.mission.MissionFragment
import app.woovictory.forearthforus.view.mypage.MyPageFragment
import app.woovictory.forearthforus.vm.MainViewModel
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_main

    override val viewModel: MainViewModel = MainViewModel()
    private var selectedItem: Int = 0
    private val TAG = javaClass.`package`?.name.toString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initStartView()
    }

    private fun addFragmentBasedOnId(itemId: Int) {
        selectedItem = itemId
        when (itemId) {
            R.id.navigation_main -> {
                changeFragment(MainFragment.newInstance()!!)
            }
            R.id.navigation_mission -> {
                changeFragment(MissionFragment.newInstance()!!)
            }
            R.id.navigation_article -> {
                changeFragment(MainFragment.newInstance()!!)
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
