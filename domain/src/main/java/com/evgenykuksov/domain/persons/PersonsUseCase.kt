package com.evgenykuksov.domain.persons

import com.evgenykuksov.domain.persons.model.ActorData
import kotlinx.coroutines.flow.Flow

interface PersonsUseCase {

    fun getActorData(id: Long): Flow<ActorData>
}