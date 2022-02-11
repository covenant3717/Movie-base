package com.evgenykuksov.domain.persons

import com.evgenykuksov.domain.persons.model.ActorInfo
import kotlinx.coroutines.flow.Flow

interface PersonsRepository {

    fun getActor(id: Long): Flow<ActorInfo>
}