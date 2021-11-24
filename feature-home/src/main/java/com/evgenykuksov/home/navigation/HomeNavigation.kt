package com.evgenykuksov.home.navigation

import androidx.navigation.fragment.FragmentNavigator

interface HomeNavigation {

    fun toMovie(movieId: Long, poster: String, extras: FragmentNavigator.Extras)
}