package com.evgenykuksov.movie.navigation

import androidx.navigation.fragment.FragmentNavigator

interface MovieNavigation {

    fun toActor(actorId: Long, actorPhoto: String, extras: FragmentNavigator.Extras)
}