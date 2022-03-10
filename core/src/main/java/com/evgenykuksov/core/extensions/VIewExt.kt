package com.evgenykuksov.core.extensions

import android.graphics.Rect
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DimenRes
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.core.view.updatePadding

fun View.gone() = this.run { isVisible = false }

fun View.visible() = this.run { isVisible = true }

fun View.invisible() = this.run { visibility = View.INVISIBLE }

fun View.setMargins(
    @DimenRes startRes: Int? = null,
    @DimenRes topRes: Int? = null,
    @DimenRes endRes: Int? = null,
    @DimenRes bottomRes: Int? = null
) {
    (layoutParams as ViewGroup.MarginLayoutParams).apply {
        val start = startRes?.let { context.dimen(it) } ?: leftMargin
        val top = topRes?.let { context.dimen(it) } ?: topMargin
        val end = endRes?.let { context.dimen(it) } ?: rightMargin
        val bottom = bottomRes?.let { context.dimen(it) } ?: bottomMargin
        setMargins(start, top, end, bottom)
    }
}

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