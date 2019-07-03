package app.woovictory.forearthforus.view.intro

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import app.woovictory.forearthforus.R
import app.woovictory.forearthforus.util.glide.GlideApp
import kotlinx.android.synthetic.main.image_slider.view.*

/**
 * Created by VictoryWoo
 */
class ImageSliderAdapter(private val context: Context) : PagerAdapter() {

    private val imageItems = arrayListOf(
        R.drawable.on_boarding_1, R.drawable.on_boarding_2
        , R.drawable.on_boarding_3
    )

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as View
    }

    override fun getCount(): Int = imageItems.size

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(context).inflate(R.layout.image_slider, container, false)


        GlideApp.with(context)
            .load(imageItems[position])
            .into(view.onBoardingImage)

        container.addView(view)

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.invalidate()
    }
}