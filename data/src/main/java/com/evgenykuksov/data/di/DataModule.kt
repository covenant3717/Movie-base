package com.evgenykuksov.data.di

import com.evgenykuksov.data.data.actors.di.actorsModule
import com.evgenykuksov.data.data.movies.di.movieModule
import com.evgenykuksov.data.data.profile.di.profileModule

val dataModules = listOf(networkModule, movieModule, profileModule, actorsModule)