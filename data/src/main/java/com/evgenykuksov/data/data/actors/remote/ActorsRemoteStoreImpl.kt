package com.evgenykuksov.data.data.actors.remote

import kotlinx.coroutines.flow.flow

internal class ActorsRemoteStoreImpl(private val api: ActorsApi) : ActorsRemoteStore {

    override fun getActorInfo(id: Long) = flow { emit(api.getActorInfo(id)) }
}