package com.evgenykuksov.data.di

fun dataModules(baseUrl: String, apiVersion: String, apiKey: String) = listOf(
    networkModule(baseUrl, apiVersion, apiKey),
)