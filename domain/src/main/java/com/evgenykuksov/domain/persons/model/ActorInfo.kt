package com.evgenykuksov.domain.persons.model

data class ActorInfo(
    val id: Long,
    val name: String,
    val birthday: String,
    val placeOfBirth: String,
    val popularity: Float,
    val biography: String,
    val profilePhoto: String
)
