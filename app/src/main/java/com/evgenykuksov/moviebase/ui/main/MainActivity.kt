package com.evgenykuksov.moviebase.ui.main

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.evgenykuksov.core.extensions.integer
import com.evgenykuksov.moviebase.R
import com.evgenykuksov.moviebase.navigation.Navigator
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

internal class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val navigator: Navigator by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initWidgets()
    }

    override fun onResume() {
        super.onResume()
        navigator.bind(findNavController(R.id.navHost))
    }

    override fun onPause() {
        super.onPause()
        navigator.unbind()
    }

    private fun initWidgets() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHost) as NavHostFragment
        bottomNavigation.setupWithNavController(navHostFragment.navController)

        navHostFragment.navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment,
                R.id.bookmarkFragment,
                R.id.profileFragment -> bottomNavigation.visibility = View.VISIBLE
                else -> {
                    Handler().postDelayed({
                        bottomNavigation.visibility = View.GONE
                    }, integer(R.integer.anim_duration_200).toLong())
                }
            }
        }
    }
}