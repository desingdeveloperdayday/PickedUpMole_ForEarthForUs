package app.woovictory.forearthforus.view.article.detail

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import app.woovictory.forearthforus.R
import app.woovictory.forearthforus.base.BaseActivity
import app.woovictory.forearthforus.databinding.ActivityArticleIntroBinding
import app.woovictory.forearthforus.model.article.detail.ArticleDetailResponse
import app.woovictory.forearthforus.view.article.adapter.ArticleDetailAdapter
import app.woovictory.forearthforus.vm.ArticleDetailViewModel

class ArticleIntroActivity : BaseActivity<ActivityArticleIntroBinding, ArticleDetailViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_article_intro

    /*
    * viewModel 의 초기화는 viewModel() 이렇게 하면 안된다.
    * ViewModelProviders.of(this).get(ViewModel::class.java)
    * 이런식으로 초기화해야 동작한다.
    *
    * TODO
    * ViewModel 초기화에 대해서 조금 더 찾아보기.
    * */
    override val viewModel: ArticleDetailViewModel
        get() = ViewModelProviders.of(this).get(ArticleDetailViewModel::class.java)

    private var itemList = ArrayList<ArticleDetailResponse>()
    private var articleDetailAdapter: ArticleDetailAdapter? = null
        set(value) {
            field = value
            field?.articleDetailLikeClickListener = {}
            field?.articleDetailImageClickListener = {}
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        articleDetailAdapter = ArticleDetailAdapter()
        getData()
        setUpData()
        initStartView()
        setUpViewModel()
        initDataBinding()
    }

    private fun getData() {
        intent?.getStringExtra("key").also {
            viewDataBinding.articleDetailTitle.text = it
        }
    }

    private fun setUpData() {
        for (i in 0..5) {
            itemList.add(
                ArticleDetailResponse(
                    R.drawable.fufe_illust_jh_04, "하나뿐인 지구, One Planet"
                    , "후원기관 : WWF"
                )
            )
        }
        articleDetailAdapter?.addItem(itemList)
    }

    override fun initStartView() {
        viewDataBinding.articleDetailRv.apply {
            adapter = articleDetailAdapter
            layoutManager = LinearLayoutManager(this@ArticleIntroActivity)
            setHasFixedSize(true)
        }
    }

    private fun setUpViewModel() {
        viewDataBinding.apply {
            vm = viewModel
            lifecycleOwner = this@ArticleIntroActivity
        }
    }

    override fun initDataBinding() {
        Log.v("0099", "들어오니? 3")
        viewModel.clickToBack.observe(this, Observer {
            Log.v("0099", "들어오니? 2")
            finish()
        })
    }
}
