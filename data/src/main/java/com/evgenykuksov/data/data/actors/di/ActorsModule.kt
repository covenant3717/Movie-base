package com.evgenykuksov.data.data.actors.di

import com.evgenykuksov.data.data.actors.ActorsRepositoryImpl
import com.evgenykuksov.data.data.actors.remote.ActorsRemoteDataSource
import com.evgenykuksov.data.data.actors.remote.ActorsRemoteStoreImpl
import com.evgenykuksov.data.data.actors.remote.ActorsApi
import com.evgenykuksov.domain.actors.ActorsRepository
import org.koin.dsl.module
import retrofit2.Retrofit

internal val actorsModule = module {

    single { get<Retrofit>().create(ActorsApi::class.java) }

    single<ActorsRemoteDataSource> { ActorsRemoteStoreImpl(get()) }

    single<ActorsRepository> { ActorsRepositoryImpl(get()) }
}