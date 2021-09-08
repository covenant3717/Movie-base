package com.evgenykuksov.moviebase.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity(@LayoutRes contentLayoutId: Int) : AppCompatActivity(contentLayoutId) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeSingleEffect()
        observeState()
        initWidgets()
    }

    protected abstract fun initWidgets()

    protected abstract fun observeState()

    protected abstract fun observeSingleEffect()
}