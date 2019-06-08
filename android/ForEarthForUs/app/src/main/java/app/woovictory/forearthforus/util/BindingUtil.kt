package app.woovictory.forearthforus.util

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.drawable.Drawable
import android.graphics.drawable.PictureDrawable
import android.net.Uri
import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import app.woovictory.forearthforus.MainActivity
import app.woovictory.forearthforus.R
import app.woovictory.forearthforus.util.glide.GlideApp
import app.woovictory.forearthforus.util.glide.SvgSoftwareLayerSetter
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.makeramen.roundedimageview.RoundedImageView

/**
 * Created by VictoryWoo
 */
object BindingUtil {

    private val requestOptions = RequestOptions()
        .centerCrop()
        .placeholder(R.color.fefu_gray)
        .error(R.color.fefu_main)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .priority(Priority.HIGH)

    @SuppressLint("StaticFieldLeak")
    private var requestBuilder: RequestBuilder<PictureDrawable>? = null



    @JvmStatic
    @BindingAdapter("android:loadImage")
    fun loadImage(imageView: ImageView, resId: Int) {
        GlideApp.with(imageView.context)
            .load(resId)
            .into(imageView)
    }

    @JvmStatic
    @BindingAdapter("android:loadUrlImage")
    fun loadImage(imageView: ImageView, path: String) {

        requestBuilder = GlideApp.with(imageView.context)
            .`as`(PictureDrawable::class.java)
            .error(R.color.fefu_white)
            .transition(DrawableTransitionOptions.withCrossFade())
            .listener(SvgSoftwareLayerSetter())

        requestBuilder?.load(Uri.parse(path))?.into(imageView)
    }

}