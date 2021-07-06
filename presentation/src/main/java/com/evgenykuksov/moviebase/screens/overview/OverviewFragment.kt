package com.evgenykuksov.moviebase.screens.overview

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.evgenykuksov.moviebase.R
import com.evgenykuksov.moviebase.base.BaseFragment

class OverviewFragment : BaseFragment(R.layout.fragment_overview) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i("ml", "dsf")
    }

    companion object {

        fun newInstance() = OverviewFragment()
    }
}