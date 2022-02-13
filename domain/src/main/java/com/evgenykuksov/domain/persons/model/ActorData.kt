package com.evgenykuksov.domain.persons.model

data class ActorData(
    val actorInfo: ActorInfo,
    val images: List<String>,
    val externalIds: PersonExternalIds
)
