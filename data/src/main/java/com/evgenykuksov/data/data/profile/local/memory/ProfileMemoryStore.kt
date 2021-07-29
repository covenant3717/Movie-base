package com.evgenykuksov.data.data.profile.local.memory

import kotlinx.coroutines.flow.Flow

internal interface ProfileMemoryStore {

    fun getRating(): Flow<Int>
}