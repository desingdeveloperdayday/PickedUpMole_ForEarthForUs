package app.woovictory.forearthforus.view.mypage.scrap

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import app.woovictory.forearthforus.R
import app.woovictory.forearthforus.base.BaseActivity
import app.woovictory.forearthforus.databinding.ActivityScrapListBinding
import app.woovictory.forearthforus.data.SharedPreferenceManager
import app.woovictory.forearthforus.view.mypage.adapter.ScrapListAdapter
import app.woovictory.forearthforus.vm.mypage.ScrapListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ScrapListActivity : BaseActivity<ActivityScrapListBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_scrap_list
    private val viewModel: ScrapListViewModel by viewModel()
    //get() = ViewModelProviders.of(this@ScrapListActivity).get(ScrapListViewModel::class.java)

    private var scrapListAdapter: ScrapListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getScrapList(SharedPreferenceManager.token)

        initStartView()
        subscribeViewModel()
    }

    override fun initStartView() {
        viewDataBinding.apply {
            vm = viewModel
            lifecycleOwner = this@ScrapListActivity
        }
    }

    private fun setUpRecyclerView() {
        viewDataBinding.scrapListRv.apply {
            adapter = scrapListAdapter
            layoutManager = LinearLayoutManager(this@ScrapListActivity)
            setHasFixedSize(true)
        }
    }

    override fun subscribeViewModel() {
        viewModel.clickToBack.observe(this, Observer {
            finish()
        })

        viewModel.scrapResponse.observe(this, Observer { items ->
            if (items.isNotEmpty()) {
                scrapListAdapter = ScrapListAdapter().apply {
                    addItem(items)
                }
                Log.v("12351 activity", items.size.toString())
                Log.v("12351 activity2 ", items.toString())
                setUpRecyclerView()
            }
        })

        viewModel.isLoading.observe(this, Observer { isLoading ->
            if (isLoading) {
                viewDataBinding.loading.visibility = View.VISIBLE
            } else {
                viewDataBinding.loading.visibility = View.GONE
            }
        })
    }
}
