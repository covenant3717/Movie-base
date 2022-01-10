package com.evgenykuksov.data.data.profile.di

import com.evgenykuksov.data.data.profile.ProfileRepositoryImpl
import com.evgenykuksov.data.data.profile.local.memory.ProfileMemoryDataSource
import com.evgenykuksov.data.data.profile.local.memory.ProfileMemoryDataSourceImpl
import com.evgenykuksov.domain.profile.ProfileRepository
import org.koin.dsl.module

internal val profileModule = module {

    single<ProfileMemoryDataSource> { ProfileMemoryDataSourceImpl() }

    single<ProfileRepository> { ProfileRepositoryImpl(get()) }
}