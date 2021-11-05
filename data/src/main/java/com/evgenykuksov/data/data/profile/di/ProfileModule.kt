package com.evgenykuksov.data.data.profile.di

import com.evgenykuksov.data.data.profile.ProfileRepositoryImpl
import com.evgenykuksov.data.data.profile.local.memory.ProfileMemoryStore
import com.evgenykuksov.data.data.profile.local.memory.ProfileMemoryStoreImpl
import com.evgenykuksov.domain.profile.ProfileRepository
import org.koin.dsl.module

internal val profileModule = module {

    single<ProfileMemoryStore> { ProfileMemoryStoreImpl() }

    single<ProfileRepository> { ProfileRepositoryImpl(get()) }
}