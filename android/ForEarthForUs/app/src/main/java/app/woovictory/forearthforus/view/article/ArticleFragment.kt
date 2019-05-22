package app.woovictory.forearthforus.view.article

import android.content.Context
import android.graphics.Point
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import app.woovictory.forearthforus.R
import app.woovictory.forearthforus.databinding.FragmentArticleBinding
import app.woovictory.forearthforus.model.article.ArticleEarthResponse
import app.woovictory.forearthforus.model.article.ArticleUsResponse
import app.woovictory.forearthforus.util.ItemDecoration
import app.woovictory.forearthforus.view.article.adapter.ArticleEarthAdapter
import app.woovictory.forearthforus.view.article.adapter.ArticleUsAdapter
import org.jetbrains.anko.support.v4.toast

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
    private var itemEarthList = ArrayList<ArticleEarthResponse>() // Earth 데이터.
    private var itemUsList = ArrayList<ArticleUsResponse>()
    private lateinit var size: Point
    private var articleEarthAdapter: ArticleEarthAdapter? = null
        set(value) {
            field = value
            field?.onArticleEarthItemClickListener = { startWebView(it) }
        }

    private var articleUsAdapter: ArticleUsAdapter? = null
        set(value) {
            field = value
            field?.articleUsItemClickListener = { position ->
                startUsDetail(position)
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

        // adapter 초기화.
        articleEarthAdapter = ArticleEarthAdapter()
        articleUsAdapter = ArticleUsAdapter()
        initImageData()
        getWindowSize()
        setUpRecyclerView()
        addSnapHelper()
    }

    private fun getWindowSize() {
        val windowManager = activity?.applicationContext?.getSystemService(Context.WINDOW_SERVICE)
                as WindowManager
        val display = windowManager.defaultDisplay
        size = Point()
        display.getSize(size)
    }


    private fun setUpRecyclerView() {
        // Earth RecyclerView 세팅.
        fragmentArticleBinding.articleEarthRv.apply {
            adapter = articleEarthAdapter
            layoutManager = LinearLayoutManager(context.applicationContext, LinearLayout.HORIZONTAL, false)
            addItemDecoration(ItemDecoration(size.x / 200, size.x / 20))
            Log.v("9988", "size.x : ${size.x}")
            Log.v("9988", "size.y : ${size.y}")
            setHasFixedSize(true)
        }
        articleEarthAdapter?.addItem(itemEarthList)

        // Us RecyclerView 세팅.
        fragmentArticleBinding.articleUsRv.apply {
            adapter = articleUsAdapter
            layoutManager = LinearLayoutManager(activity?.applicationContext, LinearLayout.HORIZONTAL, false)
            addItemDecoration(ItemDecoration(size.x / 110, size.x / 20))
            setHasFixedSize(true)
        }
        articleUsAdapter?.addItem(itemUsList)
    }

    private fun addSnapHelper() {
        var pagerSnapHelper = PagerSnapHelper()
        pagerSnapHelper.attachToRecyclerView(fragmentArticleBinding.articleEarthRv)
        //PagerSnapHelper().attachToRecyclerView(fragmentArticleBinding.articleEarthRv)
        pagerSnapHelper = PagerSnapHelper()
        pagerSnapHelper.attachToRecyclerView(fragmentArticleBinding.articleUsRv)

    }

    private fun initImageData() {

        for (i in 0..5) {
            itemEarthList.add(ArticleEarthResponse(R.drawable.fufe_illust_jh_04))
            itemUsList.add(
                ArticleUsResponse(
                    R.drawable.fufe_illust_jh_04
                    , "생태발자국 줄이기", "WWF의 하나뿐인 지구를 위한 중점사업"
                )
            )
        }
    }

    /*
    * TODO
    * 나중에 WebView 호출하는 로직 넣자.
    * */
    private fun startWebView(position: Int) {
        toast("$position 눌림")
    }

    private fun startUsDetail(position: Int) {
        toast("us $position item click")
    }
}
