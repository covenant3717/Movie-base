package com.evgenykuksov.data.data.profile

import com.evgenykuksov.data.data.profile.local.memory.ProfileMemoryStore
import com.evgenykuksov.domain.profile.ProfileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

internal class ProfileRepositoryImpl(
    private val memoryStore: ProfileMemoryStore
) : ProfileRepository {

    override fun getRating(): Flow<Int> = memoryStore.getRating()
        .flowOn(Dispatchers.IO)
}