package app.woovictory.forearthforus.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import app.woovictory.forearthforus.R
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

/**
 * Created by VictoryWoo
 */
object BindingUtil {

    val requestOptions = RequestOptions()
        .centerCrop()
        .placeholder(R.color.fefu_gray)
        .error(R.color.fefu_gray)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .priority(Priority.HIGH)

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
            .applyDefaultRequestOptions(requestOptions)
            .load(resId)
            .into(imageView)
    }
}