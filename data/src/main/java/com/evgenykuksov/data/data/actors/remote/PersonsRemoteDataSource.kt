package com.evgenykuksov.data.data.actors.remote

import com.evgenykuksov.data.data.actors.remote.model.ActorRemote
import kotlinx.coroutines.flow.Flow

internal interface PersonsRemoteDataSource {

    fun getActor(id: Long): Flow<ActorRemote>
}