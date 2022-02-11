package com.evgenykuksov.data.di

import com.evgenykuksov.data.data.persons.di.personsModule
import com.evgenykuksov.data.data.movies.di.movieModule
import com.evgenykuksov.data.data.profile.di.profileModule

fun dataModules(baseUrl: String, apiVersion: String, apiKey: String) = listOf(
    networkModule(baseUrl, apiVersion, apiKey),
    movieModule,
    profileModule,
    personsModule
)