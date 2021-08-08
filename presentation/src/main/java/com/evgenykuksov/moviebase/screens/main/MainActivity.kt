package com.evgenykuksov.moviebase.screens.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.evgenykuksov.moviebase.R
import com.evgenykuksov.moviebase.base.BaseActivity
import com.evgenykuksov.moviebase.screens.bookmarks.BookmarksFragment
import com.evgenykuksov.moviebase.screens.overview.OverviewFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.collect
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity(R.layout.activity_main) {

    private val viewModel: MainViewModel by viewModel()
    private val overviewFragment = OverviewFragment.newInstance()
    private val bookmarksFragment = BookmarksFragment.newInstance()
    private var activeFragment: Fragment = overviewFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.beginTransaction()
            .apply {
                add(R.id.container, bookmarksFragment, BookmarksFragment::class.java.simpleName).hide(bookmarksFragment)
                add(R.id.container, overviewFragment, OverviewFragment::class.java.simpleName)
            }
            .commit()
        observeState()
        initWidgets()
    }

    private fun initWidgets() {
        bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.page_overview -> viewModel.sendIntent(MainContract.Intent.OverviewTouch)
                R.id.page_list -> {}
                R.id.page_bookmark -> viewModel.sendIntent(MainContract.Intent.BookmarksTouch)
                R.id.page_profile -> {}
            }
            true
        }
    }

    private fun observeState() = lifecycleScope.launchWhenStarted {
        viewModel.state.collect {
            when (it.fragmentName) {
                OverviewFragment::class.java.simpleName -> showFragment(overviewFragment)
                BookmarksFragment::class.java.simpleName -> showFragment(bookmarksFragment)
            }
        }
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .hide(activeFragment)
            .show(fragment)
            .commit()
        activeFragment = fragment
    }
}