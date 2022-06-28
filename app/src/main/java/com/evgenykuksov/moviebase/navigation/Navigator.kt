package com.evgenykuksov.moviebase.navigation

import android.os.Bundle
import androidx.navigation.fragment.FragmentNavigator
import com.evgenykuksov.actor.ActorFragment
import com.evgenykuksov.actor.navigation.ActorNavigation
import com.evgenykuksov.home.navigation.HomeNavigation
import com.evgenykuksov.movie.MovieFragment
import com.evgenykuksov.movie.navigation.MovieNavigation
import com.evgenykuksov.moviebase.R
import com.example.feature_bottom_dialog.BottomDialogFragment

internal class Navigator : BaseNavigator(), HomeNavigation, MovieNavigation, ActorNavigation {

    /**
     * Common
     * */
    override fun back() {
        navController?.popBackStack()
    }

    override fun toYoutube(videoKey: String) {
        navController?.navigate(
            R.id.youtubeActivity,
            Bundle().apply { putString("videoKey", videoKey) }
        )
    }

    override fun toBottomDialog(listItems: List<*>) {
        navController?.navigate(
            R.id.bottomDialog,
            BottomDialogFragment.createBundle(listItems)
        )
    }

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