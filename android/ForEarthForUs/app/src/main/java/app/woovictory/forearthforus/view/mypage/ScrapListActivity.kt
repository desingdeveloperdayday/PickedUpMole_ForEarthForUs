package app.woovictory.forearthforus.view.mypage

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import app.woovictory.forearthforus.R
import app.woovictory.forearthforus.base.BaseActivity
import app.woovictory.forearthforus.databinding.ActivityScrapListBinding
import app.woovictory.forearthforus.model.article.detail.ArticleDetailResponse
import app.woovictory.forearthforus.view.mypage.adapter.ScrapListAdapter
import app.woovictory.forearthforus.vm.ScrapListViewModel

class ScrapListActivity : BaseActivity<ActivityScrapListBinding, ScrapListViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_scrap_list
    override val viewModel: ScrapListViewModel
        get() = ViewModelProviders.of(this).get(ScrapListViewModel::class.java)


    private val itemList = ArrayList<ArticleDetailResponse>()
    private var scrapListAdapter: ScrapListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpData()
        setUpAdapter()
        initStartView()
        initDataBinding()
    }

    private fun setUpData() {
        for (i in 1..5) {
            itemList.add(
                ArticleDetailResponse(
                    R.drawable.fufe_illust_jh_04, "하나뿐인 지구, One Planet"
                    , "후원기관 : WWFff"
                )
            )
        }
    }

    private fun setUpAdapter() {
        scrapListAdapter = ScrapListAdapter().apply {
            addItem(itemList)
        }
    }


    override fun initStartView() {
        viewDataBinding.scrapListRv.apply {
            adapter = scrapListAdapter
            layoutManager = LinearLayoutManager(this@ScrapListActivity)
            setHasFixedSize(true)
        }
    }

    override fun initDataBinding() {
        viewModel.clickToBack.observe(this, Observer {
            finish()
        })
    }
}
