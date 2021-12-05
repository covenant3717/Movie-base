package com.evgenykuksov.moviebase.navigation

import androidx.navigation.fragment.FragmentNavigator
import com.evgenykuksov.actor.ActorFragment
import com.evgenykuksov.home.navigation.HomeNavigation
import com.evgenykuksov.movie.MovieFragment
import com.evgenykuksov.movie.navigation.MovieNavigation
import com.evgenykuksov.moviebase.R
import kotlin.math.acos

class Navigator : BaseNavigator(), HomeNavigation, MovieNavigation {

    /**
     * HomeNavigation
     * */
    override fun toMovie(movieId: Long, poster: String, extras: FragmentNavigator.Extras) {
        navController?.navigate(
            R.id.action_homeFragment_to_movieFragment,
            MovieFragment.createBundle(movieId, poster),
            null,
            extras
        )
    }

    /**
     * MovieNavigation
     * */
    override fun toActor(actorId: Long, actorPhoto: String, extras: FragmentNavigator.Extras) {
        navController?.navigate(
            R.id.action_movieFragment_to_actorFragment,
            ActorFragment.createBundle(actorId, actorPhoto),
            null,
            extras
        )
    }
}