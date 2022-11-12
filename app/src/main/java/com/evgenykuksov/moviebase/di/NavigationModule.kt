package com.evgenykuksov.moviebase.di

import com.evgenykuksov.actor.navigation.ActorNavigation
import com.evgenykuksov.home.navigation.HomeNavigation
import com.evgenykuksov.movie.navigation.MovieNavigation
import com.evgenykuksov.moviebase.navigation.Navigator
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import org.koin.dsl.binds
import org.koin.dsl.module

val navigationModule = module {

    single { Navigator() } binds arrayOf(
        Navigator::class,
        HomeNavigation::class,
        MovieNavigation::class,
        ActorNavigation::class
    )
}

//@Module
//@InstallIn(ViewModelComponent::class)
//object NavigationModule {
//
//
//}