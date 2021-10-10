package com.evgenykuksov.core.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

abstract class BaseFragment(@LayoutRes private val layoutId: Int) : Fragment(layoutId) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeSingleEffect()
        observeState()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initWidgets()
    }

    protected abstract fun initWidgets()

    protected abstract fun observeState()

    protected abstract fun observeSingleEffect()
}