package com.evgenykuksov.data.data.profile.di

import com.evgenykuksov.data.data.profile.ProfileRepositoryImpl
import com.evgenykuksov.data.data.profile.local.memory.ProfileMemoryDataSource
import com.evgenykuksov.data.data.profile.local.memory.ProfileMemoryDataSourceImpl
import com.evgenykuksov.domain.profile.ProfileRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal object ProfileModule {

    @Provides
    fun provideProfileMemoryDataSource(): ProfileMemoryDataSource = ProfileMemoryDataSourceImpl()

    @Provides
    fun provideProfileRepository(memoryDataSource: ProfileMemoryDataSource): ProfileRepository = ProfileRepositoryImpl(memoryDataSource)
}