package com.evgenykuksov.core.anim

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.view.MotionEvent
import android.view.View
import android.view.animation.DecelerateInterpolator
import androidx.core.animation.doOnEnd
import androidx.interpolator.view.animation.FastOutSlowInInterpolator

fun View.startAnimationAlpha(
    currentValue: Float,
    newValue: Float,
    durationTime: Long = ANIM_DURATION_350,
    onAnimationEnd: () -> Unit = {}
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

fun View.animateIntChanges(
    currentValue: Int,
    newValue: Int,
    durationTime: Long = 350L,
    onAnimationUpdate: (value: Int) -> Unit = {},
    onAnimationEnd: () -> Unit = {}
) = ValueAnimator
    .ofInt(currentValue, newValue)
    .apply {
        interpolator = DecelerateInterpolator()
        duration = durationTime
        addUpdateListener {
            onAnimationUpdate(it.animatedValue as Int)
            requestLayout()
        }
        doOnEnd { onAnimationEnd() }
    }
    .start()

@SuppressLint("ClickableViewAccessibility")
fun View.setAnimScaleClickListener(onClick: () -> Unit = {}) {
    setOnTouchListener { v, event ->
        when (event.action) {
            MotionEvent.ACTION_DOWN -> startAnimationScale(0.95f) {}
            MotionEvent.ACTION_UP -> startAnimationScale(1f) { onClick() }
            MotionEvent.ACTION_CANCEL -> startAnimationScale(1f) {}
            else -> {}
        }
        false
    }
}