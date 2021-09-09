package com.evgenykuksov.data.data.actors.di

import com.evgenykuksov.data.data.actors.ActorsRepositoryImpl
import com.evgenykuksov.data.data.actors.remote.ActorsRemoteStore
import com.evgenykuksov.data.data.actors.remote.ActorsRemoteStoreImpl
import com.evgenykuksov.data.data.actors.remote.api.ActorsApi
import com.evgenykuksov.domain.actors.ActorsRepository
import org.koin.dsl.module
import retrofit2.Retrofit

internal val ActorsModule = module {

    single { get<Retrofit>().create(ActorsApi::class.java) }

    single<ActorsRemoteStore> { ActorsRemoteStoreImpl(get()) }

    single<ActorsRepository> { ActorsRepositoryImpl(get()) }
}