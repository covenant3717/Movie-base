package com.evgenykuksov.core.extensions

import android.app.Activity
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Job

fun Activity.hideStatusBar() = window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)

fun Activity.showStatusBar() = window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)

fun AppCompatActivity.launchWhenStarted(body: suspend () -> Unit): Job = lifecycleScope.launchWhenStarted { body() }