package com.evgenykuksov.moviebase.navigation

import androidx.navigation.fragment.FragmentNavigator
import com.evgenykuksov.home.navigation.HomeNavigation
import com.evgenykuksov.movie.MovieFragment
import com.evgenykuksov.moviebase.R

class Navigator : BaseNavigator(), HomeNavigation {

    override fun toMovie(movieId: Long, poster: String, extras: FragmentNavigator.Extras) {
        navController?.navigate(
            R.id.action_homeFragment_to_movieFragment,
            MovieFragment.createBundle(movieId, poster),
            null,
            extras
        )
    }
}