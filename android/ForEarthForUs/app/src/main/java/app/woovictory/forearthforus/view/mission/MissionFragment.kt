package app.woovictory.forearthforus.view.mission

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import app.woovictory.forearthforus.R

/**
 * Created by VictoryWoo
 */
class MissionFragment : Fragment() {


    companion object {
        private val ARGUMENT = "MissionFragment"
        private var fragment: MissionFragment? = null

        fun newInstance(): MissionFragment? {
            if (fragment == null) {
                fragment = MissionFragment.newInstance()
            }
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_mission, container, false)
    }


}