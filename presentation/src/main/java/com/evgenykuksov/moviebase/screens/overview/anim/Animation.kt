package com.evgenykuksov.moviebase.screens.overview.anim

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator

internal fun View.startAnimationScale(endValue: Float) {
    val scaleX = ObjectAnimator.ofFloat(this, View.SCALE_X, this.scaleX, endValue)
    val scaleY = ObjectAnimator.ofFloat(this, View.SCALE_Y, this.scaleY, endValue)
    AnimatorSet().apply {
        interpolator = AccelerateDecelerateInterpolator()
        duration = 150
        playTogether(scaleX, scaleY)
        start()
    }
}