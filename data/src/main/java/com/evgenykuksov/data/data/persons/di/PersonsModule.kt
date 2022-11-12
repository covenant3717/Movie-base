package com.evgenykuksov.data.data.persons.di

import com.evgenykuksov.data.data.persons.PersonsRepositoryImpl
import com.evgenykuksov.data.data.persons.remote.PersonsRemoteDataSource
import com.evgenykuksov.data.data.persons.remote.PersonsRemoteDataSourceImpl
import com.evgenykuksov.data.data.persons.remote.PersonsApi
import com.evgenykuksov.domain.persons.PersonsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
internal object PersonsModule {

    @Provides
    fun providePersonsApi(retrofit: Retrofit): PersonsApi = retrofit.create(PersonsApi::class.java)

    @Provides
    fun providePersonsRemoteDataSource(api: PersonsApi): PersonsRemoteDataSource = PersonsRemoteDataSourceImpl(api)

    @Provides
    fun providePersonsRepository(remoteDataSource: PersonsRemoteDataSource): PersonsRepository =
        PersonsRepositoryImpl(remoteDataSource)
}