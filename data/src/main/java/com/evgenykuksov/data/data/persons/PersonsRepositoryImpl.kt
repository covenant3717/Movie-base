package com.evgenykuksov.data.data.persons

import com.evgenykuksov.data.data.persons.remote.PersonsRemoteDataSource
import com.evgenykuksov.domain.persons.PersonsRepository
import com.evgenykuksov.domain.persons.model.ActorInfo
import com.evgenykuksov.domain.persons.model.PersonExternalIds
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class PersonsRepositoryImpl @Inject constructor(
    private val remoteDataSource: PersonsRemoteDataSource
) : PersonsRepository {

    override fun getActor(id: Long): Flow<ActorInfo> = remoteDataSource.getActor(id)
        .map { it.toDomain() }
        .flowOn(Dispatchers.IO)

    override fun getPersonImages(id: Long): Flow<List<String>> = remoteDataSource.getPersonImages(id)
        .map { it.toDomain() }
        .flowOn(Dispatchers.IO)

    override fun getPersonExternalIds(id: Long): Flow<PersonExternalIds> = remoteDataSource.getPersonExternalIds(id)
        .map { it.toDomain() }
        .flowOn(Dispatchers.IO)
}