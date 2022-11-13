package com.evgenykuksov.moviebase.di

import com.evgenykuksov.actor.navigation.ActorNavigation
import com.evgenykuksov.home.navigation.HomeNavigation
import com.evgenykuksov.movie.navigation.MovieNavigation
import com.evgenykuksov.moviebase.navigation.Navigator
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
abstract class NavigationModule {

    // Вариант 2.
    @Binds
    internal abstract fun bindHomeNavigation(navigator: Navigator): HomeNavigation

    @Binds
    internal abstract fun bindMovieNavigation(navigator: Navigator): MovieNavigation

    @Binds
    internal abstract fun bindActorNavigation(navigator: Navigator): ActorNavigation
}

@Module
@InstallIn(SingletonComponent::class)
object NavigationImplModule {

    @Provides
    @Singleton
    internal fun provideNavigator(): Navigator = Navigator()

    // Вариант 1.
//    @Provides
//    @Singleton
//    internal fun provideHomeNavigation(navigator: Navigator): HomeNavigation = navigator
//
//    @Provides
//    @Singleton
//    internal fun provideMovieNavigation(navigator: Navigator): MovieNavigation = navigator
//
//    @Provides
//    @Singleton
//    internal fun provideActorNavigation(navigator: Navigator): ActorNavigation = navigator
}