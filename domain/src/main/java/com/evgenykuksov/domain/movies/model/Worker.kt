package com.evgenykuksov.domain.movies.model

data class Worker(
    val id: Long,
    val name: String,
    val profilePath: String,
    val department: String,
    val job: String,
)
