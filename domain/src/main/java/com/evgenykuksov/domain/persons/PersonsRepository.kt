package com.evgenykuksov.domain.persons

import com.evgenykuksov.domain.persons.model.ActorInfo
import com.evgenykuksov.domain.persons.model.PersonExternalIds
import kotlinx.coroutines.flow.Flow

interface PersonsRepository {

    fun getActor(id: Long): Flow<ActorInfo>

    fun getPersonExternalIds(id: Long): Flow<PersonExternalIds>
}