package com.evgenykuksov.data.data.actors.remote

import com.evgenykuksov.data.data.actors.remote.api.ActorsApi
import kotlinx.coroutines.flow.flow

internal class ActorsRemoteStoreImpl(private val api: ActorsApi) : ActorsRemoteStore {

    override fun getActorInfo(id: Long) = flow { emit(api.getActorInfo(id)) }
}