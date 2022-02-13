package com.evgenykuksov.domain.persons.model

data class PersonExternalIds(
    val id: Long,
    val imdbId: String,
    val tvrageId: Long,
    val facebookId: String,
    val instagramId: String,
    val twitterId: String,
)
