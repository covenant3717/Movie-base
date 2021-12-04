package com.evgenykuksov.core.extensions

import android.graphics.Rect
import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding

fun View.addBottomInsets() {
    this.doOnApplyWindowInsets { view, insets, padding ->
        view.updatePadding(bottom = padding.bottom + insets.systemWindowInsetBottom)
        insets
    }
}

fun View.doOnApplyWindowInsets(block: (View, WindowInsetsCompat, Rect) -> WindowInsetsCompat) {
    val initialPadding = this.recordInitialPadding()
    ViewCompat.setOnApplyWindowInsetsListener(this) { v, insets ->
        block(v, insets, initialPadding)
    }
    this.requestApplyInsetsWhenAttached()
}

private fun View.recordInitialPadding() =
    Rect(this.paddingLeft, this.paddingTop, this.paddingRight, this.paddingBottom)

private fun View.requestApplyInsetsWhenAttached() {
    if (ViewCompat.isAttachedToWindow(this)) {
        ViewCompat.requestApplyInsets(this)
    } else {
        this.addOnAttachStateChangeListener(
            object : View.OnAttachStateChangeListener {
                override fun onViewAttachedToWindow(v: View) {
                    v.removeOnAttachStateChangeListener(this)
                    ViewCompat.requestApplyInsets(v)
                }

                override fun onViewDetachedFromWindow(v: View) {}
            })
    }
}