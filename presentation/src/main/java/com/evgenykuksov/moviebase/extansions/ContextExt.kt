package com.evgenykuksov.moviebase.extansions

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat

fun Context.toast(@StringRes resId: Int, duration: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(this, this.resources.getText(resId), duration).show()

fun Context.toast(text: String, duration: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(this, text, duration).show()

fun Context.color(@ColorRes color: Int): Int = ContextCompat.getColor(this, color)

fun Context.string(@StringRes string: Int): String = this.getString(string)

fun Context.drawable(@DrawableRes drawable: Int): Drawable? = ContextCompat.getDrawable(this, drawable)

fun Context.dimen(@DimenRes dimen: Int): Int = this.resources.getDimensionPixelOffset(dimen)
