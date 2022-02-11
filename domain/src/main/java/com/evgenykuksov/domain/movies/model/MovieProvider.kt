package com.evgenykuksov.domain.movies.model

data class MovieProvider(
    val id: Int,
    val link: String,
    val name: String,
    val logoPath: String,
    val displayPriority: Int,
)