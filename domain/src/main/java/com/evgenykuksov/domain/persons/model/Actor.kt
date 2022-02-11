package com.evgenykuksov.domain.persons.model

data class Actor(
    val id: Long,
    val name: String,
    val birthday: String,
    val placeOfBirth: String,
    val popularity: Float,
    val biography: String,
    val profilePhoto: String
)
