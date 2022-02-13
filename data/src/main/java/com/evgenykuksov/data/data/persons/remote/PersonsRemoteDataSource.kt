package com.evgenykuksov.data.data.persons.remote

import com.evgenykuksov.data.data.persons.remote.model.ActorInfoRemote
import com.evgenykuksov.data.data.persons.remote.model.PersonExternalIdsRemote
import kotlinx.coroutines.flow.Flow

internal interface PersonsRemoteDataSource {

    fun getActor(id: Long): Flow<ActorInfoRemote>

    fun getPersonExternalIds(id: Long): Flow<PersonExternalIdsRemote>
}