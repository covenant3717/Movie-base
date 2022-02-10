package com.evgenykuksov.domain.movies.model

data class MovieProviders(
    val locale: String,
    val link: String,
    val flatrate: List<MovieProvider>?,
    val rent: List<MovieProvider>?,
    val buy: List<MovieProvider>?,
)