package app.woovictory.forearthforus.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

/**
 * Created by VictoryWoo
 */
object BindingUtil {

    @JvmStatic
    @BindingAdapter("android:loadImage")
    fun loadImage(imageView: ImageView, path: String) {
        Glide.with(imageView.context)
            .load(path)
            .into(imageView)
    }

    @JvmStatic
    @BindingAdapter("android:loadImage")
    fun loadImage(imageView: ImageView, resId: Int) {
        Glide.with(imageView.context)
            .load(resId)
            .into(imageView)
    }
}