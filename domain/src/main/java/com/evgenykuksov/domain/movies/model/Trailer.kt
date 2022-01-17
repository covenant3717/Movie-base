package com.evgenykuksov.domain.movies.model

data class Trailer(
    val id: String,
    val name: String,
    val key: String,
    val site: String,
    val official: Boolean,
    val backdropPath: String,
)
