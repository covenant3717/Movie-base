package com.evgenykuksov.moviebase.extansions

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

fun Context.toast(@StringRes resId: Int, duration: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(this, this.resources.getText(resId), duration).show()

fun Context.toast(text: String, duration: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(this, text, duration).show()
