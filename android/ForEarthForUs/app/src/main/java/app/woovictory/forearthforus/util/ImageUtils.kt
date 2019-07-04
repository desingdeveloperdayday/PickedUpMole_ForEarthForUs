package app.woovictory.forearthforus.util

import android.graphics.drawable.PictureDrawable
import android.net.Uri
import android.widget.ImageView
import app.woovictory.forearthforus.R
import app.woovictory.forearthforus.util.glide.GlideApp
import app.woovictory.forearthforus.util.glide.SvgSoftwareLayerSetter
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

/**
 * Created by VictoryWoo
 */

fun loadSvgImage(imageView: ImageView, url: String) {
    GlideApp.with(imageView.context)
        .`as`(PictureDrawable::class.java)
        .error(R.color.fe_fu_white)
        .diskCacheStrategy(DiskCacheStrategy.NONE)
        .transition(DrawableTransitionOptions.withCrossFade())
        .listener(SvgSoftwareLayerSetter())
        .load(Uri.parse(url))
        .into(imageView)
}

fun loadPngImage(imageView: ImageView, url: String) {
    GlideApp.with(imageView.context)
        .load(Uri.parse(url))
        .error(R.color.fe_fu_white)
        .into(imageView)
}

fun loadDrawableImage(imageView: ImageView, drawable: Int) {
    GlideApp.with(imageView.context)
        .load(drawable)
        .error(R.color.fe_fu_white)
        .into(imageView)
}