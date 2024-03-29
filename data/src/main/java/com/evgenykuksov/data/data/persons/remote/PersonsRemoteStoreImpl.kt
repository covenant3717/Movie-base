package com.evgenykuksov.data.data.persons.remote

import kotlinx.coroutines.flow.flow

internal class PersonsRemoteStoreImpl(private val api: PersonsApi) : PersonsRemoteDataSource {

    override fun getActor(id: Long) = flow { emit(api.getActor(id)) }

    override fun getPersonImages(id: Long) = flow { emit(api.getPersonImages(id)) }

    override fun getPersonExternalIds(id: Long) = flow { emit(api.getExternalIds(id)) }
}