package com.evgenykuksov.domain.persons

import com.evgenykuksov.domain.persons.model.ActorData
import kotlinx.coroutines.flow.combine

class PersonsUseCaseImpl(private val repository: PersonsRepository) : PersonsUseCase {

    override fun getActorData(id: Long) = combine(
        repository.getActor(id),
        repository.getPersonExternalIds(id)
    ) { actorInfo, personExternalIds ->
        ActorData(actorInfo, personExternalIds)
    }
}