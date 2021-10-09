package com.evgenykuksov.core.extensions

import android.content.res.Resources
import android.util.DisplayMetrics

/**
 * Call this function on a dp value and it will return the equivalent
 * number of pixels for the current display.
 * e.g. 8.dp
 *
 * Example: recyclerView.updatePadding(top = 14.dp.toInt())
 */
val Number.dp get() = toFloat() * (Resources.getSystem().displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)

val Number.px get() = toFloat() / (Resources.getSystem().displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)