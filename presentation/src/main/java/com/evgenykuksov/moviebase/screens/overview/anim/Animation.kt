package com.evgenykuksov.moviebase.screens.overview.anim

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View
import androidx.core.animation.addListener
import androidx.interpolator.view.animation.FastOutSlowInInterpolator

internal fun View.startAnimationScale(endValue: Float, onAnimationEnd: () -> Unit) {
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