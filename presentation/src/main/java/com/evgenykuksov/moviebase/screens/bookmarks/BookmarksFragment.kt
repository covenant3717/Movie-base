package com.evgenykuksov.moviebase.screens.bookmarks

import android.os.Bundle
import android.view.View
import com.evgenykuksov.moviebase.R
import com.evgenykuksov.core.base.BaseFragment

class BookmarksFragment : BaseFragment(R.layout.fragment_bookmarks) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun initWidgets() {
        // todo
    }

    override fun observeState() {
        // todo
    }

    override fun observeSingleEffect() {
        // todo
    }

    companion object {

        fun newInstance() = BookmarksFragment()
    }
}