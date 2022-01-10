package com.evgenykuksov.data.data.profile

import com.evgenykuksov.data.data.profile.local.memory.ProfileMemoryDataSource
import com.evgenykuksov.domain.profile.ProfileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

internal class ProfileRepositoryImpl(
    private val memoryDataSource: ProfileMemoryDataSource
) : ProfileRepository {

    override fun getRating(): Flow<Int> = memoryDataSource.getRating()
        .flowOn(Dispatchers.IO)
}