package com.evgenykuksov.moviebase.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.evgenykuksov.core.extensions.launchWhenStarted
import com.evgenykuksov.core.base.BaseActivity
import com.evgenykuksov.moviebase.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.collect
import org.koin.android.ext.android.inject

class MainActivity : BaseActivity(R.layout.activity_main) {

    private val viewModel: MainViewModel by inject()
//    private val overviewFragment = OverviewFragment.newInstance()
//    private val bookmarksFragment = BookmarksFragment.newInstance()
//    private var activeFragment: Fragment = overviewFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.beginTransaction()
            .apply {
//                add(R.id.container, bookmarksFragment, BookmarksFragment::class.java.simpleName).hide(bookmarksFragment)
//                add(R.id.container, overviewFragment, OverviewFragment::class.java.simpleName)
            }
            .commit()
    }

    override fun initWidgets() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHost) as NavHostFragment
        bottomNavigation.setupWithNavController(navHostFragment.navController)

//        bottomNavigation.setOnNavigationItemSelectedListener {
//            when (it.itemId) {
//                R.id.page_overview -> viewModel.sendIntent(MainContract.Intent.OverviewTouch)
//                R.id.page_bookmark -> viewModel.sendIntent(MainContract.Intent.BookmarksTouch)
//                R.id.page_profile -> {}
//            }
//            true
//        }
    }

    override fun observeState() {
//        launchWhenStarted {
//            viewModel.state.collect {
//                when (it.fragmentName) {
////                    OverviewFragment::class.java.simpleName -> showFragment(overviewFragment)
////                    BookmarksFragment::class.java.simpleName -> showFragment(bookmarksFragment)
//                }
//            }
//        }
    }

    override fun observeSingleEffect() {}

    private fun showFragment(fragment: Fragment) {
//        supportFragmentManager.beginTransaction()
//            .hide(activeFragment)
//            .show(fragment)
//            .commit()
//        activeFragment = fragment
    }
}