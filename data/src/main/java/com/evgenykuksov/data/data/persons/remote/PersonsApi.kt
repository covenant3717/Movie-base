package com.evgenykuksov.data.data.persons.remote

import com.evgenykuksov.data.data.persons.remote.model.ActorInfoRemote
import com.evgenykuksov.data.data.persons.remote.model.PersonExternalIdsRemote
import retrofit2.http.GET
import retrofit2.http.Path

internal interface PersonsApi {

    @GET("person/{person_id}")
    suspend fun getActor(@Path("person_id") id: Long): ActorInfoRemote

    @GET("person/{person_id}/external_ids")
    suspend fun getExternalIds(@Path("person_id") id: Long): PersonExternalIdsRemote
}