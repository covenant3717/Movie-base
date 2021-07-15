package com.evgenykuksov.data.data.movies.remote.api

import com.evgenykuksov.data.data.movies.remote.model.MoviesDataRemote
import retrofit2.http.GET

interface MoviesApi {

    @GET("movie/popular")
    suspend fun getPopular(): MoviesDataRemote
}