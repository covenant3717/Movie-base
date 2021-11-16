package com.evgenykuksov.moviebase.di

import com.evgenykuksov.home.navigation.HomeNavigation
import com.evgenykuksov.moviebase.navigation.Navigator
import org.koin.dsl.binds
import org.koin.dsl.module

val navigationModule = module {

    single { Navigator() } binds arrayOf(Navigator::class, HomeNavigation::class)
}