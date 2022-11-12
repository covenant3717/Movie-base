package com.evgenykuksov.data.di

import com.evgenykuksov.data.data.persons.di.personsModule

fun dataModules(baseUrl: String, apiVersion: String, apiKey: String) = listOf(
    networkModule(baseUrl, apiVersion, apiKey),
    personsModule
)