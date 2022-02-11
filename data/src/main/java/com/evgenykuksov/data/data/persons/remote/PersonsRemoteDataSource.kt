package com.evgenykuksov.data.data.persons.remote

import com.evgenykuksov.data.data.persons.remote.model.ActorInfoRemote
import kotlinx.coroutines.flow.Flow

internal interface PersonsRemoteDataSource {

    fun getActor(id: Long): Flow<ActorInfoRemote>
}