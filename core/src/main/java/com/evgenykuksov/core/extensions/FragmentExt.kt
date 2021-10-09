package com.evgenykuksov.core.extensions

import android.content.Context
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Job

fun Fragment.hideKeyboard() = (activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
    .apply { hideSoftInputFromWindow(view?.windowToken, 0) }

fun Fragment.showKeyboard() = (activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
    .apply { toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY) }

fun Fragment.hideStatusBar() = requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)

fun Fragment.showStatusBar() = requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)

fun Fragment.launchWhenStarted(body: suspend () -> Unit): Job = lifecycleScope.launchWhenStarted { body() }