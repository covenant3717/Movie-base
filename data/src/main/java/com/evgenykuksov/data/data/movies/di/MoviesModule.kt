package com.evgenykuksov.data.data.movies.di

import com.evgenykuksov.data.data.movies.MoviesRepositoryImpl
import com.evgenykuksov.data.data.movies.remote.MoviesRemoteStore
import com.evgenykuksov.data.data.movies.remote.MoviesRemoteStoreImpl
import com.evgenykuksov.data.data.movies.remote.api.MoviesApi
import com.evgenykuksov.domain.recipes.MoviesRepository
import org.koin.dsl.module
import retrofit2.Retrofit

internal val MovieModule = module {

    single { get<Retrofit>().create(MoviesApi::class.java) }

    single<MoviesRemoteStore> { MoviesRemoteStoreImpl(get()) }

    single<MoviesRepository> { MoviesRepositoryImpl(get()) }
}