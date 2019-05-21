package app.woovictory.forearthforus.view.article

import android.content.Context
import android.graphics.Point
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import app.woovictory.forearthforus.R
import app.woovictory.forearthforus.databinding.FragmentArticleBinding
import app.woovictory.forearthforus.model.article.ArticleEarthResponse
import app.woovictory.forearthforus.util.ItemDecoration
import app.woovictory.forearthforus.view.article.adapter.ArticleEarthAdapter
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
    private var itemList = ArrayList<ArticleEarthResponse>()
    private lateinit var size: Point
    private var articleEarthAdapter: ArticleEarthAdapter? = null
        set(value) {
            field = value
            field?.onArticleEarthItemClickListener = { startWebView(it) }
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
        articleEarthAdapter = ArticleEarthAdapter()
        initImageData()
        getWindowSize()
        initRecyclerView()
        addSnapHelper()
    }

    private fun getWindowSize() {
        val windowManager = context?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = windowManager.defaultDisplay
        size = Point()
        display.getSize(size)
    }


    private fun initRecyclerView() {
        fragmentArticleBinding.articleEarthRv.apply {
            adapter = articleEarthAdapter
            layoutManager = LinearLayoutManager(context.applicationContext, LinearLayout.HORIZONTAL, false)
            addItemDecoration(ItemDecoration(size.x / 50, size.y / 15))
            setHasFixedSize(true)
        }
        articleEarthAdapter?.addItem(itemList)
    }

    private fun addSnapHelper(){
        PagerSnapHelper().attachToRecyclerView(fragmentArticleBinding.articleEarthRv)

    }

    private fun initImageData() {
        for (i in 0..5) {
            itemList.add(ArticleEarthResponse(R.drawable.fufe_illust_jh_04))
        }
    }

    private fun startWebView(position: Int) {
        toast("$position 눌림")
    }
}
