package com.evgenykuksov.moviebase.navigation

import com.evgenykuksov.home.navigation.HomeNavigation
import com.evgenykuksov.movie.MovieFragment
import com.evgenykuksov.moviebase.R

class Navigator : BaseNavigator(), HomeNavigation {

    override fun toMovie(movieId: Long) {
        navController?.navigate(R.id.action_homeFragment_to_movieFragment, MovieFragment.createBundle(movieId))
    }
}