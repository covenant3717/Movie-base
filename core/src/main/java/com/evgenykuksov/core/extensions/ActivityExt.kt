package com.evgenykuksov.core.extensions

import android.app.Activity
import android.view.WindowManager

fun Activity.hideStatusBar() = window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)

fun Activity.showStatusBar() = window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)