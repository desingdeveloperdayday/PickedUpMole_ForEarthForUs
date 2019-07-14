package app.woovictory.forearthforus.view.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import app.woovictory.forearthforus.MainActivity
import app.woovictory.forearthforus.R
import app.woovictory.forearthforus.base.BaseFragment
import app.woovictory.forearthforus.databinding.FragmentMainBinding
import app.woovictory.forearthforus.util.SharedPreferenceManager
import app.woovictory.forearthforus.util.earthLevelList
import app.woovictory.forearthforus.util.loadDrawableImage
import app.woovictory.forearthforus.view.main.adapter.MainMissionAdapter
import app.woovictory.forearthforus.view.main.detail.EarthDetailActivity
import app.woovictory.forearthforus.view.mission.MissionDetailActivity
import app.woovictory.forearthforus.vm.main.MainViewModel
import org.jetbrains.anko.support.v4.startActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by VictoryWoo
 */
class MainFragment : BaseFragment<FragmentMainBinding, MainViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_main
    override val viewModel: MainViewModel by viewModel()

    companion object {
        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }

    private var mainMissionAdapter: MainMissionAdapter? = null

    //get() = ViewModelProviders.of(this@MainFragment).get(MainViewModel::class.java)


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initStartView()
        subscribeViewModel()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getInformation()
        viewModel.getUserInformation()

        /*   if (SharedPreferenceManager.missionCompleteCount >= 0) {
               Log.v("99201", "reload 시점!!")
               mainViewModel.getInformation()
               mainViewModel.getUserInformation()
           } else {
               Log.v("99201", "reload 안함!!")
               Log.v("99201 count", SharedPreferenceManager.missionCompleteCount.toString())
               toast("변경 사항이 없음.")
           }*/
    }

    override fun initStartView() {
        viewDataBinding.apply {
            vm = viewModel
            lifecycleOwner = this@MainFragment
            itemMainEarthUserName.text = SharedPreferenceManager.userName
        }

        mainMissionAdapter = MainMissionAdapter { id, message ->
            startToDetailActivity(id, message)
        }

        viewModel.getInformation()
    }

    override fun subscribeViewModel() {
        viewModel.clickToEarthDetail.observe(this, Observer {
            startActivity<EarthDetailActivity>()
        })

        viewModel.earthResponse.observe(this, Observer {
            Log.v("40032", it.earthLevel.toString())
            Log.v("40032 ee", it.toString())
            setEarthLevel(it.earthLevel)
            //eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjoxMiwidXNlcm5hbWUiOiJsc3dAbHN3LmNvbSIsImV4cCI6MTU2MjIzODQ1OSwiZW1haWwiOiJsc3dAbHN3LmNvbSJ9.8XHuyEPqeuhlAJ-U-ZrmyfxwAHqTHzC4pIwFrLADTLg
        })

        viewModel.missionFeedResponse.observe(this, Observer {
            if (it.size > 0) {
                viewDataBinding.apply {
                    mainRv.visibility = View.VISIBLE
                    mainNullIconImage.visibility = View.GONE
                    mainMissionAdapter?.addItems(it)
                    setUpRecyclerView()
                }
            } else {
                viewDataBinding.apply {
                    mainNullIconImage.visibility = View.VISIBLE
                    mainNullIconImage.let { imageView ->
                        imageView.visibility = View.VISIBLE
                        imageView.setOnClickListener {
                            val a = activity as MainActivity
                            a.replaceFragment()
                        }
                    }
                    mainRv.visibility = View.GONE
                }
            }
        })

        viewModel.isLoading.observe(this, Observer { loading ->
            if (loading) {
                viewDataBinding.loading.visibility = View.VISIBLE
            } else {
                viewDataBinding.loading.visibility = View.GONE
            }
        })

        // earthUserResponse 구독
        // earthLevel 이 다르다면 viewModel 의 getInformation()을 호출함으로써
        // 통신을 한 번 더 진행한다. 이를 통해서 earthLevel 을 받아온다.
        viewModel.earthUserResponse.observe(this, Observer {
            if (SharedPreferenceManager.earthLevel != it.earthLevel) {
                SharedPreferenceManager.earthLevel = it.earthLevel
                viewModel.getInformation()
            }
        })
    }

    private fun setUpRecyclerView() {
        viewDataBinding.mainRv.apply {
            layoutManager = LinearLayoutManager(context.applicationContext)
            adapter = mainMissionAdapter
            setHasFixedSize(true)
        }
    }

    private fun setEarthLevel(earthLevel: Int) {
        loadDrawableImage(viewDataBinding.mainBarGraph, earthLevelList[earthLevel - 1])
    }

    private fun startToDetailActivity(id: Int, message: String) {
        val intent = Intent(context, MissionDetailActivity::class.java)
        intent.putExtra("id", id)
        Log.v("2991230",id.toString())
        intent.putExtra("completeMessage", message)
        intent.putExtra("main","main")
        startActivity(intent)
    }
}