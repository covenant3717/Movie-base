package com.evgenykuksov.data.data.actors.remote

import com.evgenykuksov.data.data.actors.remote.model.ActorRemote
import retrofit2.http.GET
import retrofit2.http.Path

internal interface PersonsApi {

    @GET("person/{person_id}")
    suspend fun getActor(@Path("person_id") id: Long): ActorRemote
}