package com.evgenykuksov.data.data.persons.remote

import com.evgenykuksov.data.data.persons.remote.model.ActorInfoRemote
import retrofit2.http.GET
import retrofit2.http.Path

internal interface PersonsApi {

    @GET("person/{person_id}")
    suspend fun getActor(@Path("person_id") id: Long): ActorInfoRemote
}