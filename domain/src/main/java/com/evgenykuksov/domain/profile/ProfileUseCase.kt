package com.evgenykuksov.domain.profile

import kotlinx.coroutines.flow.Flow

interface ProfileUseCase {

    fun getRating(): Flow<Int>
}