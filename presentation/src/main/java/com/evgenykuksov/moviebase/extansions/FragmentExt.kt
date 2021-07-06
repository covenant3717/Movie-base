package com.evgenykuksov.moviebase.extansions

import android.content.Context
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment

fun Fragment.hideKeyboard() = (activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
    .apply {
        hideSoftInputFromWindow(view?.windowToken, 0)
    }

fun Fragment.showKeyboard() = (activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
    .apply {
        toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
    }

fun Fragment.hideStatusBar() = requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)

fun Fragment.showStatusBar() = requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)