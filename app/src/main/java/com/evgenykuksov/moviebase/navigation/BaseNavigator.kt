package com.evgenykuksov.moviebase.navigation

import androidx.navigation.NavController

internal abstract class BaseNavigator {

    protected var navController: NavController? = null

    fun bind(navController: NavController) {
        this.navController = navController
    }

    fun unbind() {
        navController = null
    }
}