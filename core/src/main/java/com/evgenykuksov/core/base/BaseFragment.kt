package com.evgenykuksov.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.evgenykuksov.core.R
import com.google.android.material.appbar.MaterialToolbar

abstract class BaseFragment(@LayoutRes private val layoutId: Int) : Fragment(layoutId) {

    private var rootView: View? = null
    var hasInitializedRootView = false

    // This is a fix for re-creating a fragment.
    // todo: See for "Navigation component" updates, perhaps they will improve this behavior.
    protected fun getPersistentView(inflater: LayoutInflater?, container: ViewGroup?): View? {
        if (rootView == null) {
            // Inflate the layout for this fragment
            rootView = inflater?.inflate(layoutId, container, false)
        } else {
            // Do not inflate the layout again.
            // The returned View of onCreateView will be added into the fragment.
            // However it is not allowed to be added twice even if the parent is same.
            // So we must remove rootView from the existing parent view group
            // (it will be added back).
            (rootView?.parent as? ViewGroup)?.removeView(rootView)
        }

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeSingleEffect()
        observeState()
        if (!hasInitializedRootView) {
            hasInitializedRootView = true
            initWidgets()
        }
    }

    protected abstract fun initWidgets()

    protected abstract fun observeState()

    protected abstract fun observeSingleEffect()

    protected fun getToolbar(): MaterialToolbar? = view?.findViewById(R.id.topAppBar)
}