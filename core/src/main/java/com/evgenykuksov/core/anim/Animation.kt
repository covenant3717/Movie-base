package com.evgenykuksov.core.anim

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.view.View
import android.view.animation.DecelerateInterpolator
import androidx.core.animation.doOnEnd
import androidx.interpolator.view.animation.FastOutSlowInInterpolator

fun View.animateAlpha(
    currentValue: Float,
    newValue: Float,
    durationTime: Long = ANIM_DURATION_350,
    onAnimationEnd: () -> Unit
) = ValueAnimator
    .ofFloat(currentValue, newValue)
    .apply {
        interpolator = DecelerateInterpolator()
        duration = durationTime
        addUpdateListener { alpha = it.animatedValue as Float }
        doOnEnd { onAnimationEnd() }
    }
    .start()

fun View.startAnimationScale(endValue: Float, onAnimationEnd: () -> Unit) {
    val scaleX = ObjectAnimator.ofFloat(this, View.SCALE_X, this.scaleX, endValue)
    val scaleY = ObjectAnimator.ofFloat(this, View.SCALE_Y, this.scaleY, endValue)
    AnimatorSet().apply {
        interpolator = FastOutSlowInInterpolator()
        duration = ANIM_DURATION_100
        playTogether(scaleX, scaleY)
        doOnEnd { onAnimationEnd() }
        start()
    }
}

fun View.startAnimationScaleWithBackward(endValue: Float, onAnimationEnd: () -> Unit) {
    startAnimationScale(endValue) {
        startAnimationScale(1f) { onAnimationEnd() }
    }
}
