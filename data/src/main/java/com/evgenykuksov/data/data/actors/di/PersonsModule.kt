package com.evgenykuksov.data.data.actors.di

import com.evgenykuksov.data.data.actors.PersonsRepositoryImpl
import com.evgenykuksov.data.data.actors.remote.PersonsRemoteDataSource
import com.evgenykuksov.data.data.actors.remote.PersonsRemoteStoreImpl
import com.evgenykuksov.data.data.actors.remote.PersonsApi
import com.evgenykuksov.domain.persons.PersonsRepository
import org.koin.dsl.module
import retrofit2.Retrofit

internal val personsModule = module {

    single { get<Retrofit>().create(PersonsApi::class.java) }

    single<PersonsRemoteDataSource> { PersonsRemoteStoreImpl(get()) }

    single<PersonsRepository> { PersonsRepositoryImpl(get()) }
}