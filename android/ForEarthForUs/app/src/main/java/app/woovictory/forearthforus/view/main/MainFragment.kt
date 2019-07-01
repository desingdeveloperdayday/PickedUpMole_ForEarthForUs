package app.woovictory.forearthforus.view.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.app.ActivityOptionsCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import app.woovictory.forearthforus.R
import app.woovictory.forearthforus.databinding.FragmentMainBinding
import app.woovictory.forearthforus.util.SharedPreferenceManager
import app.woovictory.forearthforus.util.glide.GlideApp
import app.woovictory.forearthforus.view.main.detail.EarthDetailActivity
import app.woovictory.forearthforus.view.mission.MissionDetailActivity
import app.woovictory.forearthforus.vm.main.MainViewModel
import org.jetbrains.anko.support.v4.startActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by VictoryWoo
 */
class MainFragment : Fragment() {

    companion object {
        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }

    private lateinit var fragmentMainBinding: FragmentMainBinding
    private var mainMissionAdapter: MainMissionAdapter? = null
    private val mainViewModel: MainViewModel by viewModel()
    private var earthLevelList = arrayListOf(R.drawable.main_bar_graph1, R.drawable.main_bar_graph2)

    //get() = ViewModelProviders.of(this@MainFragment).get(MainViewModel::class.java)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentMainBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        return fragmentMainBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
        setUpViewModel()
        setUpDataBinding()
    }

    private fun init() {
        mainMissionAdapter = MainMissionAdapter {
            startToDetailActivity(it)
        }
        fragmentMainBinding.itemMainEarthUserName.text = SharedPreferenceManager.userName
    }

    private fun setUpViewModel() {
        fragmentMainBinding.apply {
            vm = mainViewModel
            lifecycleOwner = this@MainFragment
        }
    }

    private fun setUpDataBinding() {
        mainViewModel.getInformation()

        mainViewModel.clickToEarthDetail.observe(this, Observer {
            startActivity<EarthDetailActivity>()
        })

        mainViewModel.earthResponse.observe(this, Observer {
            Log.v("40032", it.earthLevel.toString())
            setEarthLevel(it.earthLevel - 1)
        })

        mainViewModel.missionFeedResponse.observe(this, Observer {
            Log.v("22883", it.size.toString())
            if (it.size > 0) {
                fragmentMainBinding.apply {
                    mainRv.visibility = View.VISIBLE
                    mainMissionAdapter?.addItems(it)
                    setUpRecyclerView()
                }
                fragmentMainBinding.mainNullIconImage.visibility = View.GONE
            } else {
                fragmentMainBinding.mainNullIconImage.visibility = View.VISIBLE
                fragmentMainBinding.mainRv.visibility = View.GONE
            }
        })

        mainViewModel.isLoading.observe(this, Observer { loading ->
            if (loading) {
                fragmentMainBinding.loading.visibility = View.VISIBLE
            } else {
                fragmentMainBinding.loading.visibility = View.GONE
            }
        })
    }

    private fun setUpRecyclerView() {
        fragmentMainBinding.mainRv.apply {
            layoutManager = LinearLayoutManager(context.applicationContext)
            adapter = mainMissionAdapter
            setHasFixedSize(true)
        }
    }

    private fun setEarthLevel(earthLevel: Int) {
        setEarthBarImage(earthLevel)
    }

    private fun setEarthBarImage(earthLevel: Int) {
        GlideApp.with(this).load(earthLevelList[earthLevel]).into(fragmentMainBinding.mainBarGraph)
    }

    private fun startToDetailActivity(id: Int) {
        val intent = Intent(context, MissionDetailActivity::class.java)
        intent.putExtra("categoryId", id)
        intent.putExtra("url", "main")
        startActivity(intent)
    }
}