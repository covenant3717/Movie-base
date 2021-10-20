package com.evgenykuksov.core.anim

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View
import androidx.core.animation.addListener
import androidx.core.view.isVisible
import androidx.interpolator.view.animation.FastOutSlowInInterpolator

fun View.startAnimationAlpha(endValue: Float, duration: Long, onAnimationEnd: () -> Unit) {
    ObjectAnimator.ofFloat(this, View.ALPHA, endValue).apply {
        interpolator = FastOutSlowInInterpolator()
        this.duration = duration
        addListener(
            onEnd = { onAnimationEnd() }
        )
        start()
    }
}

fun View.fadeTo(visible: Boolean, duration: Long = 500, startDelay: Long = 0, toAlpha: Float = 1f) {
    if (visible && alpha == 1f) alpha = 0f
    animate()
        .alpha(if (visible) toAlpha else 0f)
        .withStartAction { if (visible) isVisible = true }
        .withEndAction { if (isAttachedToWindow && !visible) isVisible = false }
        .setInterpolator(FastOutSlowInInterpolator())
        .setDuration(duration)
        .setStartDelay(startDelay)
        .start()
}

fun View.startAnimationScale(endValue: Float, onAnimationEnd: () -> Unit) {
    val scaleX = ObjectAnimator.ofFloat(this, View.SCALE_X, this.scaleX, endValue)
    val scaleY = ObjectAnimator.ofFloat(this, View.SCALE_Y, this.scaleY, endValue)
    AnimatorSet().apply {
        interpolator = FastOutSlowInInterpolator()
        duration = 100
        playTogether(scaleX, scaleY)
        addListener(
            onEnd = { onAnimationEnd() }
        )
        start()
    }
}

fun View.startAnimationScaleWithBackward(endValue: Float, onAnimationEnd: () -> Unit) {
    startAnimationScale(endValue) {
        startAnimationScale(1f) { onAnimationEnd() }
    }
}
