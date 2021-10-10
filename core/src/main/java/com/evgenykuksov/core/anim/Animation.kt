package com.evgenykuksov.core.anim

import android.animation.ObjectAnimator
import android.view.View
import androidx.core.animation.addListener
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