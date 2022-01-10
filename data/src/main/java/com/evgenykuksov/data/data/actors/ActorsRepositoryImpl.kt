package com.evgenykuksov.data.data.actors

import com.evgenykuksov.data.data.actors.remote.ActorsRemoteDataSource
import com.evgenykuksov.domain.actors.ActorsRepository
import com.evgenykuksov.domain.actors.model.ActorInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

internal class ActorsRepositoryImpl(
    private val remoteDataSource: ActorsRemoteDataSource
) : ActorsRepository {

    override fun getActorInfo(id: Long): Flow<ActorInfo> = remoteDataSource.getActorInfo(id)
        .map { it.toDomain() }
        .flowOn(Dispatchers.IO)
}