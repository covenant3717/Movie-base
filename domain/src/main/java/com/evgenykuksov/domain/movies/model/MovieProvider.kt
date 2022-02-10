package com.evgenykuksov.domain.movies.model

data class MovieProvider(
    val id: Int,
    val name: String,
    val logoPath: String,
    val displayPriority: Int,
)