package com.evgenykuksov.domain.persons

import com.evgenykuksov.domain.persons.model.Actor
import kotlinx.coroutines.flow.Flow

interface PersonsUseCase {

    fun getActor(id: Long): Flow<Actor>
}