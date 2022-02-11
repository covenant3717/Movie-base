package com.evgenykuksov.data.data.persons.di

import com.evgenykuksov.data.data.persons.PersonsRepositoryImpl
import com.evgenykuksov.data.data.persons.remote.PersonsRemoteDataSource
import com.evgenykuksov.data.data.persons.remote.PersonsRemoteStoreImpl
import com.evgenykuksov.data.data.persons.remote.PersonsApi
import com.evgenykuksov.domain.persons.PersonsRepository
import org.koin.dsl.module
import retrofit2.Retrofit

internal val personsModule = module {

    single { get<Retrofit>().create(PersonsApi::class.java) }

    single<PersonsRemoteDataSource> { PersonsRemoteStoreImpl(get()) }

    single<PersonsRepository> { PersonsRepositoryImpl(get()) }
}