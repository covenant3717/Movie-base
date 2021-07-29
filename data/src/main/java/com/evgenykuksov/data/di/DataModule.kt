package com.evgenykuksov.data.di

import com.evgenykuksov.data.data.movies.di.MovieModule
import com.evgenykuksov.data.data.movies.di.ProfileModule

val DataModule = listOf(NetworkModule, MovieModule, ProfileModule)