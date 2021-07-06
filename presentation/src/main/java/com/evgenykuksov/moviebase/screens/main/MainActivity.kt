package com.evgenykuksov.moviebase.screens.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.evgenykuksov.moviebase.R
import com.evgenykuksov.moviebase.base.BaseActivity
import com.evgenykuksov.moviebase.screens.bookmarks.BookmarksFragment
import com.evgenykuksov.moviebase.screens.overview.OverviewFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity(R.layout.activity_main) {

    private val viewModel: MainViewModel by viewModel()
    private val overviewFragment by lazy { OverviewFragment.newInstance() }
    private val bookmarksFragment by lazy { BookmarksFragment.newInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initWidgets()
    }

    private fun initWidgets() {
        setFragment(overviewFragment)
        bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.page_overview -> true.also { setFragment(overviewFragment) }
                R.id.page_list -> true
                R.id.page_bookmark -> true.also { setFragment(bookmarksFragment) }
                R.id.page_profile -> true
                else -> false
            }
        }
    }

    private fun setFragment(fragment: Fragment) = supportFragmentManager
        .beginTransaction()
        .replace(R.id.fragmentContainer, fragment)
        .commit()
}