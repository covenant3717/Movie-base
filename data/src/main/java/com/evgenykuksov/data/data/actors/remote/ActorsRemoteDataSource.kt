package com.evgenykuksov.data.data.actors.remote

import com.evgenykuksov.data.data.actors.remote.model.ActorInfoRemote
import kotlinx.coroutines.flow.Flow

internal interface ActorsRemoteDataSource {

    fun getActorInfo(id: Long): Flow<ActorInfoRemote>
}