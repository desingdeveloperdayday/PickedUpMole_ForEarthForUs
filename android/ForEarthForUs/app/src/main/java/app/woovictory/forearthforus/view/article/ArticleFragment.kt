package app.woovictory.forearthforus.view.article

import android.content.Context
import android.content.Intent
import android.graphics.Point
import android.net.Uri
import android.os.Bundle
import android.view.WindowManager
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import app.woovictory.forearthforus.R
import app.woovictory.forearthforus.base.BaseFragment
import app.woovictory.forearthforus.databinding.FragmentArticleBinding
import app.woovictory.forearthforus.utils.decoration.ItemDecoration
import app.woovictory.forearthforus.data.SharedPreferenceManager
import app.woovictory.forearthforus.view.article.adapter.ArticleEarthAdapter
import app.woovictory.forearthforus.view.article.adapter.ArticleListAdapter
import app.woovictory.forearthforus.view.article.detail.ArticleDetailActivity
import app.woovictory.forearthforus.vm.article.ArticleViewModel
import org.jetbrains.anko.support.v4.startActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by VictoryWoo
 */
class ArticleFragment : BaseFragment<FragmentArticleBinding, ArticleViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_article
    override val viewModel: ArticleViewModel by viewModel()

    companion object {
        fun newInstance(): ArticleFragment {
            return ArticleFragment()
        }
    }

    private lateinit var size: Point
    private var articleEarthAdapter: ArticleEarthAdapter? = null
        set(value) {
            field = value
            field?.onArticleEarthItemClickListener = { startWebView(it) }
        }

    private var articleListAdapter: ArticleListAdapter? = null
        set(value) {
            field = value
            field?.articleUsItemClickListener = { link ->
                startWebView(link)
            }
        }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initAdapter()
        getWindowSize()
        addSnapHelper()
        initStartView()
        subscribeViewModel()
    }

    // adapter 초기화.
    private fun initAdapter() {
        articleEarthAdapter = ArticleEarthAdapter()
        articleListAdapter = ArticleListAdapter()
    }

    override fun initStartView() {
        viewDataBinding.apply {
            vm = viewModel
            lifecycleOwner = this@ArticleFragment
        }

        // 요청.
        viewModel.getDonationList(SharedPreferenceManager.token)
        viewModel.getArticleList(SharedPreferenceManager.token)
    }

    override fun subscribeViewModel() {
        viewModel.clickToArticleEarthDetail.observe(this, Observer {
            startActivity<ArticleDetailActivity>("key" to "For Earth")
        })
        viewModel.clickToArticleUsDetail.observe(this, Observer {
            startActivity<ArticleDetailActivity>("key" to "For Us")
        })

        viewModel.donationResponse.observe(this, Observer {
            if (it.isNotEmpty()) {
                articleEarthAdapter?.addItem(it)
            }
        })

        viewModel.articleResponse.observe(this, Observer {
            if (it.isNotEmpty()) {
                articleListAdapter?.addItem(it)
                setUpRecyclerView()
            }
        })
    }

    private fun getWindowSize() {
        val windowManager = activity?.applicationContext?.getSystemService(Context.WINDOW_SERVICE)
                as WindowManager
        val display = windowManager.defaultDisplay
        size = Point()
        display.getSize(size)
    }


    private fun setUpRecyclerView() {
        // Donation RecyclerView
        viewDataBinding.articleEarthRv.apply {
            adapter = articleEarthAdapter
            layoutManager = LinearLayoutManager(context.applicationContext, LinearLayout.HORIZONTAL, false)
            addItemDecoration(ItemDecoration(size.x / 80, size.x / 20))
            setHasFixedSize(true)
        }

        // 아티클 리싸이클러뷰
        viewDataBinding.articleUsRv.apply {
            adapter = articleListAdapter
            layoutManager = LinearLayoutManager(activity?.applicationContext, LinearLayout.HORIZONTAL, false)
            addItemDecoration(ItemDecoration(size.x / 110, size.x / 20))
            setHasFixedSize(true)
        }
    }

    private fun addSnapHelper() {
        var pagerSnapHelper = PagerSnapHelper()
        pagerSnapHelper.attachToRecyclerView(viewDataBinding.articleEarthRv)
        //PagerSnapHelper().attachToRecyclerView(fragmentArticleBinding.articleEarthRv)
        pagerSnapHelper = PagerSnapHelper()
        pagerSnapHelper.attachToRecyclerView(viewDataBinding.articleUsRv)

    }

    private fun startWebView(link: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(link)
        startActivity(intent)
    }
}