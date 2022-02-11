package com.evgenykuksov.data.data.persons

import com.evgenykuksov.data.data.persons.remote.PersonsRemoteDataSource
import com.evgenykuksov.domain.persons.PersonsRepository
import com.evgenykuksov.domain.persons.model.ActorInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

internal class PersonsRepositoryImpl(
    private val remoteDataSource: PersonsRemoteDataSource
) : PersonsRepository {

    override fun getActor(id: Long): Flow<ActorInfo> = remoteDataSource.getActor(id)
        .map { it.toDomain() }
        .flowOn(Dispatchers.IO)
}