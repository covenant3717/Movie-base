package com.evgenykuksov.moviebase.ui.main

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.evgenykuksov.core.base.BaseActivity
import com.evgenykuksov.moviebase.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun initWidgets() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHost) as NavHostFragment
        bottomNavigation.setupWithNavController(navHostFragment.navController)

        navHostFragment.navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment,
                R.id.bookmarkFragment,
                R.id.profileFragment -> bottomNavigation.visibility = View.VISIBLE
                else -> bottomNavigation.visibility = View.GONE
            }
        }
    }

    override fun observeState() {}

    override fun observeSingleEffect() {}
}