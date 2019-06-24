package app.woovictory.forearthforus.view.article.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import app.woovictory.forearthforus.R
import app.woovictory.forearthforus.base.BaseActivity
import app.woovictory.forearthforus.databinding.ActivityArticleDetailBinding
import app.woovictory.forearthforus.util.SharedPreferenceManager
import app.woovictory.forearthforus.view.article.adapter.ArticleDetailAdapter
import app.woovictory.forearthforus.vm.article.ArticleDetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ArticleDetailActivity : BaseActivity<ActivityArticleDetailBinding, ArticleDetailViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_article_detail

    /*
    * viewModel 의 초기화는 viewModel() 이렇게 하면 안된다.
    * ViewModelProviders.of(this).get(ViewModel::class.java)
    * 이런식으로 초기화해야 동작한다.
    *
    * TODO
    * ViewModel 초기화에 대해서 조금 더 찾아보기.
    * */
    override val viewModel: ArticleDetailViewModel by viewModel()
    //ViewModelProviders.of(this).get(ArticleDetailViewModel::class.java)
    private var articleDetailAdapter: ArticleDetailAdapter? = null
        set(value) {
            field = value
            field?.articleDetailLikeClickListener = {}
            field?.articleDetailImageClickListener = {}
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // adapter 초기화.
        articleDetailAdapter = ArticleDetailAdapter()
        getData()
        initStartView()
        initDataBinding()
        // 비동기 호출이기 때문에 후원처 리스트를 불러오는 동안 함수가 아래를 타고 내려간다.
        // 따라서 여기서 호출하는 것이 아니라 구독하는 곳에서 호출해야 한다.
        //setUpRecyclerView()
    }

    private fun getData() {
        intent?.getStringExtra("key").also {
            viewDataBinding.articleDetailTitle.text = it
            when (it) {
                "For Earth" -> { // 기부 후원처 리스트.
                    viewModel.getDetailList(SharedPreferenceManager.token)
                }
                "For Us" -> {

                }
            }
        }
    }

    override fun initStartView() {
        viewDataBinding.apply {
            vm = viewModel
            lifecycleOwner = this@ArticleDetailActivity
        }

    }

    override fun initDataBinding() {
        viewModel.clickToBack.observe(this, Observer {
            finish()
        })

        viewModel.articleDetailResponse.observe(this, Observer {
            if (it.isNotEmpty()) {
                Log.v("0099", it.size.toString())
                articleDetailAdapter?.addItem(it)
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

    private fun setUpRecyclerView() {
        viewDataBinding.articleDetailRv.apply {
            adapter = articleDetailAdapter
            layoutManager = LinearLayoutManager(this@ArticleDetailActivity)
            setHasFixedSize(true)
        }
    }
}
