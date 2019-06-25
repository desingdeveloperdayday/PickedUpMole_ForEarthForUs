package app.woovictory.forearthforus.view.article

import android.content.Context
import android.content.Intent
import android.graphics.Point
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import app.woovictory.forearthforus.R
import app.woovictory.forearthforus.databinding.FragmentArticleBinding
import app.woovictory.forearthforus.model.article.ArticleEarthResponse
import app.woovictory.forearthforus.model.article.ArticleUsResponse
import app.woovictory.forearthforus.model.article.DonationResponse
import app.woovictory.forearthforus.util.ItemDecoration
import app.woovictory.forearthforus.util.SharedPreferenceManager
import app.woovictory.forearthforus.view.article.adapter.ArticleEarthAdapter
import app.woovictory.forearthforus.view.article.adapter.ArticleListAdapter
import app.woovictory.forearthforus.view.article.detail.ArticleDetailActivity
import app.woovictory.forearthforus.vm.article.ArticleViewModel
import org.jetbrains.anko.support.v4.startActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by VictoryWoo
 */
class ArticleFragment : Fragment() {

    companion object {
        fun newInstance(): ArticleFragment {
            return ArticleFragment()
        }
    }

    private lateinit var fragmentArticleBinding: FragmentArticleBinding
    private val articleViewModel: ArticleViewModel by viewModel()
    private var itemEarthList = ArrayList<ArticleEarthResponse>() // Earth 데이터.
    private var itemUsList = ArrayList<ArticleUsResponse>()

    private lateinit var itemDonationList: ArrayList<DonationResponse>
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentArticleBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_article, container
            , false
        )
        return fragmentArticleBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // 요청.
        articleViewModel.getDonationList(SharedPreferenceManager.token)
        articleViewModel.getArticleList(SharedPreferenceManager.token)

        initAdapter()
        getWindowSize()
        addSnapHelper()
        setViewModel()
        setUpDataBinding()
    }

    private fun initAdapter() {
        // adapter 초기화.
        articleEarthAdapter = ArticleEarthAdapter()
        articleListAdapter = ArticleListAdapter()
    }

    private fun setViewModel() {
        fragmentArticleBinding.apply {
            viewModel = articleViewModel
            lifecycleOwner = this@ArticleFragment
        }
    }

    private fun setUpDataBinding() {
        articleViewModel.clickToArticleEarthDetail.observe(this, Observer {
            startActivity<ArticleDetailActivity>("key" to "For Earth")
        })
        articleViewModel.clickToArticleUsDetail.observe(this, Observer {
            startActivity<ArticleDetailActivity>("key" to "For Us")
        })

        articleViewModel.donationResponse.observe(this, Observer {
            if (it.isNotEmpty()) {
                articleEarthAdapter?.addItem(it)
            }
        })

        articleViewModel.articleResponse.observe(this, Observer {
            if(it.isNotEmpty()){
                articleListAdapter?.addItem(it)
            }
        })

        setUpRecyclerView()
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
        fragmentArticleBinding.articleEarthRv.apply {
            adapter = articleEarthAdapter
            layoutManager = LinearLayoutManager(context.applicationContext, LinearLayout.HORIZONTAL, false)
            addItemDecoration(ItemDecoration(size.x / 80, size.x / 20))
            setHasFixedSize(true)
        }

        // 아티클 리싸이클러뷰
        fragmentArticleBinding.articleUsRv.apply {
            adapter = articleListAdapter
            layoutManager = LinearLayoutManager(activity?.applicationContext, LinearLayout.HORIZONTAL, false)
            addItemDecoration(ItemDecoration(size.x / 110, size.x / 20))
            setHasFixedSize(true)
        }
    }

    private fun addSnapHelper() {
        var pagerSnapHelper = PagerSnapHelper()
        pagerSnapHelper.attachToRecyclerView(fragmentArticleBinding.articleEarthRv)
        //PagerSnapHelper().attachToRecyclerView(fragmentArticleBinding.articleEarthRv)
        pagerSnapHelper = PagerSnapHelper()
        pagerSnapHelper.attachToRecyclerView(fragmentArticleBinding.articleUsRv)

    }

    private fun startWebView(link: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(link)
        startActivity(intent)
        //toast("$link 눌림")
    }

}
