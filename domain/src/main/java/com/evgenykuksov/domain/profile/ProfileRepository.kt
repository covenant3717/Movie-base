package com.evgenykuksov.domain.profile

import kotlinx.coroutines.flow.Flow

interface ProfileRepository {

    fun getRating(): Flow<Int>
}