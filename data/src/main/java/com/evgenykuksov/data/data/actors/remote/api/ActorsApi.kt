package com.evgenykuksov.data.data.actors.remote.api

import com.evgenykuksov.data.data.actors.remote.model.ActorInfoRemote
import retrofit2.http.GET
import retrofit2.http.Path

internal interface ActorsApi {

    @GET("person/{person_id}")
    suspend fun getActorInfo(@Path("person_id") id: Long): ActorInfoRemote
}