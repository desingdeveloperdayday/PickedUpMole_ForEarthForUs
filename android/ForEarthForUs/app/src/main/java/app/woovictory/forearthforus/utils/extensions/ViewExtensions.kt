package app.woovictory.forearthforus.utils.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import app.woovictory.forearthforus.R
import app.woovictory.forearthforus.utils.glide.GlideApp

/**
 * Created by VictoryWoo
 * View Extensions
 */

fun ImageView.loadDrawableImage(drawable: Int) {
    GlideApp.with(this)
        .load(drawable)
        .error(R.color.fe_fu_white)
        .into(this)
}

fun Activity.hideKeyboard(context: Context) {
    this.currentFocus?.let { view ->
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Drawable.changeColorFilter(color: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        this.colorFilter = BlendModeColorFilter(color, BlendMode.SRC_ATOP)
    } else {
        @Suppress("DEPRECATION")
        this.setColorFilter(color, PorterDuff.Mode.SRC_ATOP)
    }
}

fun <T> Context.openActivity(activity: Class<T>) {
    Intent(this, activity).apply {
        startActivity(this)
    }
}