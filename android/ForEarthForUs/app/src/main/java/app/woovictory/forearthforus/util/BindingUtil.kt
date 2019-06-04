package app.woovictory.forearthforus.util

import android.app.Activity
import android.graphics.drawable.Drawable
import android.net.Uri
import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import app.woovictory.forearthforus.MainActivity
import app.woovictory.forearthforus.R
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou

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



    /*@JvmStatic
    @BindingAdapter("android:loadImageString")
    fun loadImage(imageView: ImageView, path: String) {
        Log.v("11882 path", path.toString())
        Glide.with(imageView.context)
            .applyDefaultRequestOptions(requestOptions)
            .load(path)
            .into(imageView)
    }*/

  /*  @JvmStatic
    @BindingAdapter("android:loadImage")
    fun loadImage(imageView: ImageView, path: String?) {
        Log.v("11882 path", path.toString())
        if (path != null) {
            Log.v("11882 path", "if 안으로?!")
            Glide.with(imageView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(path)
                .listener(object : RequestListener<Drawable>{
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        Log.v("11882 fail", "${e?.message}")
                        Log.v("11882 fail", "${e?.printStackTrace()}")
                        return true
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        Log.v("11882 success", "성공.")
                        return true
                    }

                })
                .into(imageView)
        }
        Log.v("11882 path", "여기까지 떨어지니?")
    }
*/



    @JvmStatic
    @BindingAdapter("android:loadImage")
    fun loadImage(imageView: ImageView, resId: Int) {
        Glide.with(imageView.context)
            .load(resId)
            .into(imageView)
    }

    @JvmStatic
    @BindingAdapter("android:loadImage")
    fun loadImage(imageView: ImageView, path: String) {
        //GlideToVectorYou.justLoadImage(MainActivity().applicationContext as Activity, Uri.parse(path), imageView)
        Glide.with(imageView.context)
            .applyDefaultRequestOptions(requestOptions)
            .load(path)
            .into(imageView)
    }
}