package com.evgenykuksov.domain.actors

import com.evgenykuksov.domain.actors.model.ActorInfo
import kotlinx.coroutines.flow.Flow

interface ActorsUseCase {

    fun getActorInfo(id: Long): Flow<ActorInfo>
}