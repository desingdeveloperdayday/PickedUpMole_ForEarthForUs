package app.woovictory.forearthforus.util

import android.widget.ImageView
import app.woovictory.forearthforus.R
import app.woovictory.forearthforus.util.glide.GlideApp

/**
 * Created by VictoryWoo
 */

fun loadDrawableImage(imageView: ImageView, drawable: Int) {
    GlideApp.with(imageView.context)
        .load(drawable)
        .error(R.color.fe_fu_white)
        .into(imageView)
}