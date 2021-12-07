package com.evgenykuksov.movie.navigation

import androidx.navigation.fragment.FragmentNavigator
import com.evgenykuksov.core.base.BaseNavigation

interface MovieNavigation: BaseNavigation {

    fun toActor(actorId: Long, actorPhoto: String, extras: FragmentNavigator.Extras)
}