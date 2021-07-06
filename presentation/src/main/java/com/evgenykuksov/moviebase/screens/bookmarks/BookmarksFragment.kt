package com.evgenykuksov.moviebase.screens.bookmarks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.evgenykuksov.moviebase.R
import com.evgenykuksov.moviebase.base.BaseFragment

class BookmarksFragment : BaseFragment(R.layout.fragment_bookmarks) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    companion object {

        fun newInstance() = BookmarksFragment()
    }
}