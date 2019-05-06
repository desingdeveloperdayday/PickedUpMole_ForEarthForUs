package app.woovictory.forearthforus

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import app.woovictory.forearthforus.view.mission.MissionFragment
import app.woovictory.forearthforus.view.mypage.MyPageFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    override fun onNavigationItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.navigation_main -> {
            toast("ddd?")
            changeFragment(MissionFragment.newInstance()!!)
            true
        }
        R.id.navigation_mission -> {
            MissionFragment.newInstance()?.let {
                changeFragment(it)
            }
            true
        }
        R.id.navigation_article -> {
            true
        }
        R.id.navigation_my -> {
            changeFragment(MyPageFragment.newInstance())
            true
        }
        else -> true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainBottomNavigation.setOnNavigationItemSelectedListener(this@MainActivity)

    }


    private fun changeFragment(fragment: Fragment) {
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.mainFrameContainer, fragment).commit()
    }
}
