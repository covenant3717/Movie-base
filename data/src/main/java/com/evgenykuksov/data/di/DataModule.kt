package com.evgenykuksov.data.di

import com.evgenykuksov.data.data.actors.di.ActorsModule
import com.evgenykuksov.data.data.movies.di.MovieModule
import com.evgenykuksov.data.data.profile.di.ProfileModule

val DataModule = listOf(NetworkModule, MovieModule, ProfileModule, ActorsModule)