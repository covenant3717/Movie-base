package com.evgenykuksov.data.data.movies.di

import com.evgenykuksov.data.data.movies.MoviesRepositoryImpl
import com.evgenykuksov.data.data.movies.remote.MoviesRemoteStore
import com.evgenykuksov.data.data.movies.remote.MoviesRemoteStoreImpl
import com.evgenykuksov.data.data.movies.remote.MoviesApi
import com.evgenykuksov.domain.movies.MoviesRepository
import org.koin.dsl.module
import retrofit2.Retrofit

internal val movieModule = module {

    single { get<Retrofit>().create(MoviesApi::class.java) }

    single<MoviesRemoteStore> { MoviesRemoteStoreImpl(get()) }

    single<MoviesRepository> { MoviesRepositoryImpl(get()) }
}