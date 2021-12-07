package com.evgenykuksov.home.navigation

import androidx.navigation.fragment.FragmentNavigator
import com.evgenykuksov.core.base.BaseNavigation

interface HomeNavigation: BaseNavigation {

    fun toMovie(movieId: Long, poster: String, extras: FragmentNavigator.Extras)
}