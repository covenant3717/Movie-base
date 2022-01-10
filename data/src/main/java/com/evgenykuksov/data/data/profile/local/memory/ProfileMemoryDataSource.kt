package com.evgenykuksov.data.data.profile.local.memory

import kotlinx.coroutines.flow.Flow

internal interface ProfileMemoryDataSource {

    fun getRating(): Flow<Int>
}