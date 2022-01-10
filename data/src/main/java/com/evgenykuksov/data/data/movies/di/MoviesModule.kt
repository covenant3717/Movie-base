package com.evgenykuksov.data.data.movies.di

import com.evgenykuksov.data.data.movies.MoviesRepositoryImpl
import com.evgenykuksov.data.data.movies.remote.MoviesRemoteDataSource
import com.evgenykuksov.data.data.movies.remote.MoviesRemoteDataSourceImpl
import com.evgenykuksov.data.data.movies.remote.MoviesApi
import com.evgenykuksov.domain.movies.MoviesRepository
import org.koin.dsl.module
import retrofit2.Retrofit

internal val movieModule = module {

    single { get<Retrofit>().create(MoviesApi::class.java) }

    single<MoviesRemoteDataSource> { MoviesRemoteDataSourceImpl(get()) }

    single<MoviesRepository> { MoviesRepositoryImpl(get()) }
}