package app.woovictory.forearthforus.util

import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator

/**
 * Created by VictoryWoo
 * 애니메이션 구현을 위해 만든 클래스.. 어떻게 쓰는지,, 아직..
 */
class CustomSlideView(
    val view: View,
    private val currentHeight: Int, private val newHeight: Int
) {

    var slideAnimator: ValueAnimator = ValueAnimator
        .ofInt(currentHeight, newHeight)
        .setDuration(500)

    fun setSlideAnimation(){
        slideAnimator.addUpdateListener { animation->
            /*animation.animatedValue.let {
                view.layoutParams.height = it
            }*/
            val value = animation.animatedValue as Int
            value.apply {
                view.layoutParams.height = this
                view.requestLayout()
            }
        }

        AnimatorSet().apply {
            interpolator = AccelerateDecelerateInterpolator()
            play(slideAnimator)
            start()
        }

    }
}