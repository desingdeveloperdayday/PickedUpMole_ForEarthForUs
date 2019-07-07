package app.woovictory.forearthforus.util

import android.annotation.SuppressLint
import android.graphics.drawable.PictureDrawable
import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import app.woovictory.forearthforus.R
import app.woovictory.forearthforus.util.glide.GlideApp
import app.woovictory.forearthforus.util.glide.SvgSoftwareLayerSetter
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

/**
 * Created by VictoryWoo
 */
object BindingUtil {

    /*private val requestOptions = RequestOptions()
        .centerCrop()
        .placeholder(R.color.fe_fu_gray)
        .error(R.color.fe_fu_main)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .priority(Priority.HIGH)*/

    @SuppressLint("StaticFieldLeak")
    private var requestBuilder: RequestBuilder<PictureDrawable>? = null


    @JvmStatic
    @BindingAdapter("android:loadPngImage")
    fun loadPngImage(imageView: ImageView, path: String) {
        GlideApp.with(imageView.context)
            .load(path)
            .into(imageView)
    }

    @JvmStatic
    @BindingAdapter("android:loadUrlImage")
    fun loadImage(imageView: ImageView, path: String) {

        requestBuilder = GlideApp.with(imageView.context)
            .`as`(PictureDrawable::class.java)
            .error(R.color.fe_fu_white)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .transition(DrawableTransitionOptions.withCrossFade())
            .listener(SvgSoftwareLayerSetter())

        requestBuilder?.load(Uri.parse(path))?.into(imageView)
    }
}