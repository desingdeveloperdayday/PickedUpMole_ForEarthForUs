package app.woovictory.forearthforus.view.article.detail

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import app.woovictory.forearthforus.R
import app.woovictory.forearthforus.base.BaseActivity
import app.woovictory.forearthforus.databinding.ActivityArticleDetailBinding
import app.woovictory.forearthforus.model.article.detail.ArticleDetailResponse
import app.woovictory.forearthforus.view.article.adapter.ArticleDetailAdapter
import app.woovictory.forearthforus.vm.ArticleDetailViewModel

class ArticleDetailActivity : BaseActivity<ActivityArticleDetailBinding, ArticleDetailViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_article_detail
    override val viewModel: ArticleDetailViewModel
        get() = ArticleDetailViewModel()

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
        setUpData()
        initStartView()
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
    }

    override fun initStartView() {
        viewDataBinding.articleDetailRv.apply {
            adapter = articleDetailAdapter
            layoutManager = LinearLayoutManager(this@ArticleDetailActivity)
            setHasFixedSize(true)
        }
    }

    override fun initDataBinding() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
